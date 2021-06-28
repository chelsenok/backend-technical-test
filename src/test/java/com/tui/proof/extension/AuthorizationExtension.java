package com.tui.proof.extension;

import com.tui.proof.service.SecurityService;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class AuthorizationExtension implements BeforeEachCallback {

    private final Supplier<SecurityService> securityServiceSupplier;

    public AuthorizationExtension(Supplier<SecurityService> securityServiceSupplier) {
        this.securityServiceSupplier = securityServiceSupplier;
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        boolean authorized = extensionContext.getTestMethod()
                .map(method -> method.getAnnotationsByType(Authorized.class))
                .map(annotations -> annotations.length != 0)
                .orElse(false);
        if (authorized) {
            doReturn(true).when(securityServiceSupplier.get()).authenticate(any());
        }
    }
}
