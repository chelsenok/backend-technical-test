package com.tui.proof.repository;

import com.tui.proof.exception.NotFoundException;
import com.tui.proof.model.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Booking repository
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class BookingRepository {

    private final Map<UUID, Booking> BOOKING_STORAGE = new ConcurrentHashMap<>();

    /**
     * Find all bookings from storage.
     *
     * @return all bookings from storage
     */
    public List<Booking> findAll() {
        return new ArrayList<>(BOOKING_STORAGE.values());
    }

    /**
     * Find booking by uuid.
     *
     * @param uuid uuid of booking
     * @return found booking
     * @throws NotFoundException if there is no booking by such uuid in storage
     */
    public Booking findByUuid(UUID uuid) {
        Booking booking = BOOKING_STORAGE.get(uuid);
        if (booking == null) {
            log.warn("Booking was not found by uuid {}", uuid);
            throw new NotFoundException(MessageFormatter.format("Booking was not found by uuid {}", uuid).getMessage());
        }
        return booking;
    }

    /**
     * Save booking into the storage
     *
     * @param booking booking to save
     * @return saved booking
     */
    public Booking save(Booking booking) {
        UUID key = UUID.randomUUID();
        booking.setUuid(key);
        BOOKING_STORAGE.put(key, booking);
        return booking;
    }
}
