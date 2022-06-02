package org.wildhamsters.loginservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
