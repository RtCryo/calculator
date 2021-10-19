package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dao.UsersRepository;
import org.example.model.SecurityUserModel;
import org.example.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserModel userModel = usersRepository.findByUsername(s);
            return SecurityUserModel.fromUser(userModel);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }
}
