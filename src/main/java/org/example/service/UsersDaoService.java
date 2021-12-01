package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.UsersRepository;
import org.example.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersDaoService {
    private final UsersRepository usersRepository;

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) usersRepository.findAll();
    }

    public UserModel createNewUser(UserModel userModel) { return usersRepository.save(userModel); }
}
