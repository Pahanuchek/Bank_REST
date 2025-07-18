package com.example.bankcards.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserRegisterDtoRequest {
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private Set<String> roles;
}
