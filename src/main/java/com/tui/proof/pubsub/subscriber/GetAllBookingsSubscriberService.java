package com.tui.proof.pubsub.subscriber;

import com.tui.proof.model.Booking;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetAllBookingsMessage;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllBookingsSubscriberService extends SubscriberService {

    private final BookingService bookingService;

    @Override
    protected void processMessage(Message message) {
        GetAllBookingsMessage getAllBookingsMessage = (GetAllBookingsMessage) message;
        List<Booking> allBookings = bookingService.getAllBookings();
        getAllBookingsMessage.getBookings().addAll(allBookings);
    }

    @Override
    public List<Topic> getTopics() {
        return Collections.singletonList(Topic.GET_ALL_BOOKINGS);
    }
}
