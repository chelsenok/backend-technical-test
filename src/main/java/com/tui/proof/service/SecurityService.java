package com.tui.proof.service;

import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.exception.UnauthorizedException;
import com.tui.proof.model.Role;
import com.tui.proof.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * Security service
 */
@Slf4j
@Service
public class SecurityService {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    @Value("#{${api.authorization.tokens}}")
    private Map<Role, String> authorizationTokens;

    /**
     * Authenticating user by provided token
     *
     * @param token token
     * @return authentication status
     */
    public boolean authenticate(String token) {
        log.info("Trying authenticate with token {}", token);
        Optional<Role> role = authorizationTokens.entrySet().stream()
                .filter(entry -> entry.getValue().equals(token))
                .map(Map.Entry::getKey)
                .findFirst();

        if (!role.isPresent()) {
            log.error("Failed to authenticate user with token {}", token);
            return false;
        }

        log.info("Successfully authenticated user with token {}", token);
        currentUser.set(new User(role.get()));
        return true;
    }

    /**
     * Assert that user is authorized
     *
     * @throws UnauthorizedException if user is not authorized
     */
    public void assertUserAuthorized() {
        getCurrentUserOrThrowUnauthorizedException();
    }

    /**
     * Assert that current user has admin rights
     *
     * @throws ForbiddenException if user is not admin
     */
    public void assertCurrentUserAdmin() {
        User currentUser = getCurrentUserOrThrowUnauthorizedException();
        if (!currentUser.getRole().equals(Role.ADMIN)) {
            log.error("Current user does not have admin rights");
            throw new ForbiddenException("Current user has no admin rights.");
        }
    }

    /**
     * Get current user
     *
     * @return authorized user model
     */
    public User getCurrentUser() {
        return currentUser.get();
    }

    /**
     * Get current user or throw runtime exception
     *
     * @return authorized user model
     * @throws UnauthorizedException if user is not authorized
     */
    public User getCurrentUserOrThrowUnauthorizedException() {
        User user = currentUser.get();
        if (user == null) {
            log.error("User is not authorized");
            throw new UnauthorizedException("User is not authorized.");
        }
        return user;
    }
}
