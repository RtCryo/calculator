package org.example.controller;

import org.example.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/navbarRequest")
public class NavbarController {

    @GetMapping()
    public @ResponseBody ResponseEntity<UserDTO> userNameNavbar(@AuthenticationPrincipal User user) {
        if(user == null) {
            return new ResponseEntity<>(new UserDTO("Anon"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UserDTO(user.getUsername()), HttpStatus.OK);
        }
    }
}
