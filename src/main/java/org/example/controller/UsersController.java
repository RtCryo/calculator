package org.example.controller;

import org.example.model.UserModel;
import org.example.service.UsersDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsersController {

    private final UsersDaoService usersDaoService;

    public UsersController(UsersDaoService usersDaoService) {
        this.usersDaoService = usersDaoService;
    }

    @GetMapping("/userslist")
    public String usersListController(){
        return "userslist";
    }

    @GetMapping("/userslist/getlist")
    public @ResponseBody ResponseEntity<List<UserModel>> usersGetListController(){
        return new ResponseEntity<>(usersDaoService.getAllUsers(), HttpStatus.OK);
    }
}
