package com.oauth.quickhelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String home() {
        return "forward:/Log-in-template.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/Log-in-template.html";
    }
}
