package org.wildhamsters.loginservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

class LoginserviceSuccessLogoutHandler extends
        SimpleUrlLogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String userName = authentication.getName();
        LoginserviceApplication.JEDIS.del(userName);
        super.onLogoutSuccess(request, response, authentication);
    }
}
