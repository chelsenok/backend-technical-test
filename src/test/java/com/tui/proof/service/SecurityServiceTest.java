package com.tui.proof.service;

import com.tui.proof.MainApplication;
import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainApplication.class)
@TestPropertySource("classpath:application.test.properties")
public class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    @Test
    public void testFailedAuthenticate() {
        assertFalse(securityService.authenticate("test"));
        doThrow(UnauthorizedException.class).when(spy(securityService)).assertUserAuthorized();
        doThrow(UnauthorizedException.class).when(spy(securityService)).getCurrentUserOrThrowUnauthorizedException();
        assertNull(securityService.getCurrentUser());
    }

    @Test
    public void testCommonAuthenticate() {
        assertTrue(securityService.authenticate("common"));
        assertDoesNotThrow(() -> securityService.assertUserAuthorized());
        doThrow(ForbiddenException.class).when(spy(securityService)).assertCurrentUserAdmin();
        assertNotNull(securityService.getCurrentUser());
    }

    @Test
    public void testAdminAuthenticate() {
        assertTrue(securityService.authenticate("admin"));
        assertDoesNotThrow(() -> securityService.assertUserAuthorized());
        assertDoesNotThrow(() -> securityService.assertCurrentUserAdmin());
        assertNotNull(securityService.getCurrentUser());
    }
}
