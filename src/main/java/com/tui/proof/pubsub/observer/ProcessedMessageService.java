package com.tui.proof.pubsub.observer;

import com.tui.proof.exception.NotFoundException;
import com.tui.proof.pubsub.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
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

@Slf4j
@Service
public class ProcessedMessageService implements Observer {

    private static final ConcurrentHashMap<UUID, Message> PROCESSED_MESSAGES = new ConcurrentHashMap<>();

    @Value("${api.event.processed-message.lifetime-minutes}")
    private int processedMessageLifetimeMinutes;

    @Override
    public void update(Observable o, Object arg) {
        log.info("{} is notified by {} with object: {}", this.getClass().getSimpleName(), o.getClass().getSimpleName(), arg);
        if (arg instanceof Message) {
            Message message = (Message) arg;
            message.setProcessedAt(LocalDateTime.now());
            PROCESSED_MESSAGES.put(message.getUuid(), message);
            log.info("Message {} was put in processed messages storage", message.getUuid());
        } else {
            log.info("{} is notified by unsupported message from {} with object: {}", this.getClass().getSimpleName(),
                    o.getClass().getSimpleName(), arg);
            throw new UnsupportedOperationException("ProcessedMessageObserver supports only Message object for notifications");
        }
    }

    @Scheduled(fixedRateString = "${api.event.processed-message.check-rate-millis}")
    private void checkProcessedMessagesMap() {
        log.info("Checking processed message storage for cleaning up");
        Predicate<Message> lifetimeExpired = message -> Duration.between(message.getProcessedAt(), LocalDateTime.now())
                .get(ChronoUnit.MINUTES) > processedMessageLifetimeMinutes;
        Predicate<Message> hasRead = Message::isRead;

        PROCESSED_MESSAGES.entrySet().parallelStream()
                .map(Map.Entry::getValue)
                .filter(hasRead.or(lifetimeExpired))
                .map(Message::getUuid)
                .peek(uuid -> log.info("Message {} will be cleaned from processed messages storage", uuid))
                .forEach(PROCESSED_MESSAGES::remove);
    }

    public Message get(UUID uuid) {
        log.info("Searching for message {}", uuid);
        Message message = PROCESSED_MESSAGES.get(uuid);
        if (message == null) {
            log.warn("Message was not found by {}", uuid);
            throw new NotFoundException(MessageFormatter.format("Message by uuid '{}' not found", uuid).getMessage());
        }
        log.info("Set read flag to found message {}", uuid);
        message.setRead(true);
        return message;
    }
}
