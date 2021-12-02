package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.UsersRepository;
import org.example.dto.UserDTO;
import org.example.model.Status;
import org.example.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersDaoService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) usersRepository.findAll();
    }

    public UserModel getUser(String name){
        return usersRepository.findByUsername(name);
    }

    public String createNewUser(UserDTO userDTO) {
        if(usersRepository.findByUsername(userDTO.getUsername()) == null)
        {
            UserModel userModel = new UserModel();
            userModel.setUsername(userDTO.getUsername());
            userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userModel.setRole(userDTO.getRole());
            userModel.setStatus(Status.ACTIVE);
            userModel.setEnabled(true);
            return usersRepository.save(userModel).equals(userModel)?"User is created":"User is undefined";
        }
        return "User is exist";
    }
}
