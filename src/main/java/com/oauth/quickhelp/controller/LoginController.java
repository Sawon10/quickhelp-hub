package com.oauth.quickhelp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/")
    public String home() {
        return "Log-in-template";
    }

    @GetMapping("/login")
    public String login() {
        return "Log-in-template";
    }
}
