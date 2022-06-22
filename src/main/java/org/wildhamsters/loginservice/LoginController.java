package org.wildhamsters.loginservice;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
    private AuthenticationManager authManager = new AuthenticationManager() {

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String name = authentication.getName();
            String session = authentication.getCredentials().toString();

            if (check(name, session)) {
                LoginserviceApplication.JEDIS.set(name, RequestContextHolder.currentRequestAttributes().getSessionId());
                System.out.println(String.format("%s setting user %s session", session, name));
                return new UsernamePasswordAuthenticationToken(
                        name, session, new ArrayList<>());
            } else {
                return authentication;
            }
        }

        boolean check(String name, String session) {
            String userSession = LoginserviceApplication.JEDIS.get(name);
            return userSession != null && userSession.equals(session);
        }
    };

    @GetMapping
    String hello() {
        return "register.html";
    }

    @GetMapping("/register")
    String showRegistrationPage() {
        return "register.html";
    }

    @GetMapping("/welcome")
    String showWelcomePage() {
        return "welcome.html";
    }

    @GetMapping("/stats")
    String showStatisticsPage() {
        return "stats.html";
    }

    @GetMapping("/game")
    RedirectView redirectToGameRoom(RedirectAttributes attributes, Principal user) {
        String userName = user.getName();
        String session = LoginserviceApplication.JEDIS.get(userName);
        attributes.addAttribute("userName", userName);
        attributes.addAttribute("sessionId", session);
        return new RedirectView("http://localhost:8080/gameroom");
    }

    @GetMapping("/menu")
    String showMenu(@RequestParam(name = "userName", required = false, defaultValue = "defaultUser") String userName,
            @RequestParam(name = "sessionId", required = false, defaultValue = "defaultSession") String sessionId,
            HttpServletRequest req) {

        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(userName, sessionId);
        Authentication authenticatedUser = authManager.authenticate(loginToken);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        System.out.println(String.format("%s authorization user %s ", sessionId, userName));
        if (authenticatedUser.isAuthenticated())
            return "welcome.html";
        else
            return "register.html";
    }
}
