package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTO;
import org.example.service.UsersDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UsersDaoService usersDaoService;

    @GetMapping()
    public ResponseEntity<UserDTO> getLoginPage(@AuthenticationPrincipal User user) {
        UserDTO userDTO = new UserDTO();
        if(user == null) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(usersDaoService.getUser(user.getUsername()).getRole());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
