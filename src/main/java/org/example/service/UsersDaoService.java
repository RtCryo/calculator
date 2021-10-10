package org.example.service;

import org.example.dao.UsersRepository;
import org.example.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersDaoService {
    private final UsersRepository usersRepository;

    public UsersDaoService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) usersRepository.findAll();
    }
}
