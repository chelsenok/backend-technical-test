package com.tui.proof.pubsub.observer;

import com.tui.proof.pubsub.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Service
public class ProcessedMessageObserver implements Observer {

    private final static ConcurrentHashMap<UUID, Message> PROCESSED_MESSAGES = new ConcurrentHashMap<>();

    @Value("${api.flight-availability.lifetime-minutes}")
    private int processedMessageLifetimeMinutes;

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Message) {
            Message message = (Message) arg;
            message.setProcessedAt(LocalDateTime.now());
            PROCESSED_MESSAGES.put(message.getUuid(), message);
        }
        throw new UnsupportedOperationException("ProcessedMessageObserver supports only Message object for notifications");
    }

    @Scheduled(fixedRateString = "${api.event.processed-message.check-rate-millis}")
    private void checkProcessedMessagesMap() {
        Predicate<Message> lifetimeExpired = message -> Duration.between(message.getProcessedAt(), LocalDateTime.now())
                .get(ChronoUnit.MINUTES) > processedMessageLifetimeMinutes;
        Predicate<Message> hasRead = Message::isRead;

        PROCESSED_MESSAGES.entrySet().parallelStream()
                .map(Map.Entry::getValue)
                .filter(hasRead.or(lifetimeExpired))
                .map(Message::getUuid)
                .forEach(PROCESSED_MESSAGES::remove);
    }
}
