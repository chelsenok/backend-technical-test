package com.tui.proof.service;

import com.tui.proof.exception.ForbiddenException;
import com.tui.proof.exception.UnauthorizedException;
import com.tui.proof.model.Role;
import com.tui.proof.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class SecurityService {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    @Value("#{${api.authorization.tokens}}")
    private Map<Role, String> authorizationTokens;

    public boolean authenticate(String token) {
        Optional<Role> role = authorizationTokens.entrySet().stream()
                .filter(entry -> entry.getValue().equals(token))
                .map(Map.Entry::getKey)
                .findFirst();

        if (!role.isPresent()) {
            return false;
        }

        currentUser.set(new User(role.get()));
        return true;
    }

    public void assertUserAuthorized() {
        getCurrentUserOrThrowUnauthorizedException();
    }

    public void assertCurrentUserAdmin() {
        User currentUser = getCurrentUserOrThrowUnauthorizedException();
        if (!currentUser.getRole().equals(Role.ADMIN)) {
            throw new ForbiddenException("Current user has no admin rights.");
        }
    }

    public User getCurrentUser() {
        return currentUser.get();
    }

    public User getCurrentUserOrThrowUnauthorizedException() {
        User user = currentUser.get();
        if (user == null) {
            throw new UnauthorizedException("User is not authorized.");
        }
        return user;
    }
}
