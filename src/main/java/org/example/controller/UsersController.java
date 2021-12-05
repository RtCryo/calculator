package org.example.controller;

import org.example.dto.MessageDTO;
import org.example.dto.UserDTO;
import org.example.model.UserModel;
import org.example.service.UsersDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('admin:read')")
public class UsersController {

    private final UsersDaoService usersDaoService;

    public UsersController(UsersDaoService usersDaoService) {
        this.usersDaoService = usersDaoService;
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> usersGetListController(){
        return new ResponseEntity<>(usersDaoService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> usersPostCreateController(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(new MessageDTO(usersDaoService.createNewUser(userDTO)), HttpStatus.OK);
    }
}
