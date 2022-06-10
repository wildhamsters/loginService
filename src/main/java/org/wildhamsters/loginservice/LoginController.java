package org.wildhamsters.loginservice;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

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
    public RedirectView redirectToGameRoom(RedirectAttributes attributes, Principal user) {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        attributes.addAttribute("userName", user.getName());
        attributes.addAttribute("sessionId", session);
        System.out.println(LoginserviceApplication.JEDIS.get("session"));
        return new RedirectView("http://localhost:8080/gameroom");
    }

}
