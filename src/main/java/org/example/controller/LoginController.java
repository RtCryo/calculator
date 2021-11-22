package org.example.controller;

import org.example.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public ResponseEntity<UserDTO> getLoginPage(@AuthenticationPrincipal User user) {
        UserDTO userDTO = new UserDTO();
        if(user == null) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        userDTO.setUsername(user.getUsername());
        userDTO.setAuthorities(user.getAuthorities());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
