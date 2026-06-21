package com.oauth.quickhelp.controller;

import com.oauth.quickhelp.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final RegistrationService registrationService;

    public LoginController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String home() {
        return "forward:/register.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/register.html";
    }

    @GetMapping("/recruiters")
    public String recruiters() {
        return "forward:/recruiters.html";
    }

    @GetMapping("/helpers")
    public String helpers() {
        return "forward:/helpers.html";
    }

    @GetMapping("/register")
    public String register() {
        return "forward:/register.html";
    }

    @GetMapping("/register/recruiter")
    public String registerRecruiter() {
        return "forward:/register-recruiter.html";
    }

    @GetMapping("/register/helper")
    public String registerHelper() {
        return "forward:/register-helper.html";
    }
}
