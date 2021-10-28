package org.example.controller;

import org.example.DTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NavbarController {

    @GetMapping("/navbarRequest")
    public @ResponseBody ResponseEntity<UserDTO> userNameNavbar(@AuthenticationPrincipal User user) {
        if(user == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UserDTO(user.getUsername()), HttpStatus.OK);
        }
    }
}
