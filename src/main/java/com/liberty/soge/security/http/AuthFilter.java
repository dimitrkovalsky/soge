package com.liberty.soge.security.http;

import com.liberty.soge.security.AuthenticationService;
import com.liberty.soge.security.UserBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Filter for resolving and refreshing authentication using OKTA token cookie.
 */
@Slf4j
@Component
public class AuthFilter extends DelegatingFilterProxy {


    public static final String TOKEN = "Soge-Token";

    @Autowired
    private AuthenticationService authService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        if (isSecuredUrl(request)) {
            String token = request.getHeader(TOKEN);
            if (token == null) {
                log.error("Unauthorized access for " + request.getRequestURI());
                sendUnauthorized(res);
                return;
            } else {
                if (!checkToken(token))
                    return;
            }
        }
        chain.doFilter(req, res);
    }

    private boolean isSecuredUrl(HttpServletRequest request) {
        return request.getRequestURI().contains("/api");
    }

    private void sendUnauthorized(ServletResponse res) throws IOException {
        HttpServletResponse servletResponse = (HttpServletResponse) res;
        servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
    }

    private boolean checkToken(String token) {
        Optional<UserBase> user = authService.getUser(token);
        return user.isPresent();
    }


}
