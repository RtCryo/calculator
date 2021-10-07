package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "index";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "calc";
    }
}
