package com.tui.proof.controller;

import com.tui.proof.exception.BadRequestException;
import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.service.BookingService;
import com.tui.proof.service.SecurityService;
import com.tui.proof.validation.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;
    @MockBean
    private SecurityService securityService;
    @MockBean
    private ValidationService validationService;

    @Test
    public void testUnauthorized() throws Exception {
        doReturn(false).when(securityService).authenticate(any());

        mockMvc.perform(get("/api/v1/bookings"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAcceptedGetAllBookings() throws Exception {
        doReturn(true).when(securityService).authenticate(any());

        mockMvc.perform(get("/api/v1/bookings"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetAllBookingsByNotAdmin() throws Exception {
        doReturn(true).when(securityService).authenticate(any());
        doThrow(new ForbiddenException("forbidden")).when(securityService).assertCurrentUserAdmin();

        mockMvc.perform(get("/api/v1/bookings"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testBadRequestWhileCreateBooking() throws Exception {
        doReturn(true).when(securityService).authenticate(any());
        doThrow(new BadRequestException(Collections.singletonList("bad_request"))).when(validationService).validate(any());

        mockMvc
                .perform(post("/api/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
