package com.tui.proof.configuration;

import com.tui.proof.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {

    private final SecurityService securityService;

    @Value("${api.authorization.header}")
    private String authorizationHeader;

    @Value("#{'${api.authorization.urls}'.split(',')}")
    private List<String> authorizationUrls;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationToken = ((HttpServletRequest) servletRequest).getHeader(authorizationHeader);
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        boolean needAuthorization = authorizationUrls.stream().anyMatch(requestURI::contains);

        if (needAuthorization && !securityService.authenticate(authorizationToken)) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
