package org.example.service;

import org.example.dao.UsersRepository;
import org.example.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserModel userModel = usersRepository.findByUsername(s);
            return SecurityUser.fromUser(userModel);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }
}
