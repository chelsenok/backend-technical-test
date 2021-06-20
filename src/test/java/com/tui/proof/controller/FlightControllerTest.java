package com.tui.proof.controller;

import com.tui.proof.exception.BadRequestException;
import com.tui.proof.extension.AuthorizationExtension;
import com.tui.proof.extension.Authorized;
import com.tui.proof.service.FlightService;
import com.tui.proof.service.FlightsAvailabilityService;
import com.tui.proof.service.SecurityService;
import com.tui.proof.validation.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;
    @MockBean
    private SecurityService securityService;
    @RegisterExtension
    AuthorizationExtension authorizationExtension = new AuthorizationExtension(() -> securityService);
    @MockBean
    private ValidationService validationService;
    @MockBean
    private FlightsAvailabilityService flightsAvailabilityService;

    @Test
    public void testUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/flights"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Authorized
    public void testAcceptedGetAllFlights() throws Exception {
        mockMvc.perform(get("/api/v1/flights"))
                .andExpect(status().isAccepted())
                .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    @Authorized
    public void testBadRequestWhileSearch() throws Exception {
        doThrow(new BadRequestException(Collections.singletonList("bad_request"))).when(validationService).validate(any());

        mockMvc
                .perform(post("/api/v1/flights/search_availabilities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
