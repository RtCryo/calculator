package org.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/getUserName")
    @ResponseBody
    public String getUserName(Authentication authentication){
        return authentication.getName();
    }
}
