package org.example.controller;

import org.example.model.UserModel;
import org.example.service.UsersDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('admin:read')")
public class UsersController {

    private final UsersDaoService usersDaoService;

    public UsersController(UsersDaoService usersDaoService) {
        this.usersDaoService = usersDaoService;
    }

    @GetMapping("/users")
    public String usersController(){
        return "userslist";
    }

    @GetMapping("/userslist/getlist")
    public ResponseEntity<List<UserModel>> usersGetListController(){
        return new ResponseEntity<>(usersDaoService.getAllUsers(), HttpStatus.OK);
    }
}
