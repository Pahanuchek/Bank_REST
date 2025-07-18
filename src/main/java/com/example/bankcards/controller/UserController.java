package com.example.bankcards.controller;

import com.example.bankcards.dto.request.user.UserAuthDtoRequest;
import com.example.bankcards.dto.request.user.UserRegisterDtoRequest;
import com.example.bankcards.dto.response.user.UserAuthDtoResponse;
import com.example.bankcards.dto.response.user.UserRegisterDtoResponse;
import com.example.bankcards.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRegisterDtoResponse userRegister(@RequestBody UserRegisterDtoRequest userDtoRequest) {
        return userService.userRegister(userDtoRequest);
    }

    @PostMapping(path = "/auth",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAuthDtoResponse userAuth(@RequestBody UserAuthDtoRequest userDtoRequest) {
        return userService.userAuth(userDtoRequest);
    }
}
