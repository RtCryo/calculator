package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Role;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String username;
    private String password;
    private Role role;
}
