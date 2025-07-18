package com.example.bankcards.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDtoResponse {
    private String jwtToken;
    private long id;
    private String login;
    private Set<String> roles;
}
