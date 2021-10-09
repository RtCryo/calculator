package org.example.service;

import org.example.dao.UsersRepository;
import org.example.model.SecurityUserModel;
import org.example.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserModel userModel = usersRepository.findByUsername(s);
            return SecurityUserModel.fromUser(userModel);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }
}
