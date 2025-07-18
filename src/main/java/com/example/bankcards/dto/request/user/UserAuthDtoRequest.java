package com.example.bankcards.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthDtoRequest {
    private long id;
    private String login;
    private String password;
}
