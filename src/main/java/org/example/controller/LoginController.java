package org.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", defaultValue = "false") boolean loginError, Model model,
                               @AuthenticationPrincipal User user) {
        if(user != null) { return "redirect:/calc"; }
        if(loginError) {
            model.addAttribute(
                    "errorMessage",
                    "Username or password is incorrect. Please make sure to provide valid username or password.");
        }
        return "index";
    }
}
