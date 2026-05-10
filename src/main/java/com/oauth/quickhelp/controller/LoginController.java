package com.oauth.quickhelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String home() {
        return "forward:/landing.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/landing.html";
    }

    @GetMapping("/recruiters")
    public String recruiters() {
        return "forward:/recruiters.html";
    }

    @GetMapping("/helpers")
    public String helpers() {
        return "forward:/helpers.html";
    }
}
