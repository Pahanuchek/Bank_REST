package com.example.bankcards.mapper.user.impl;

import com.example.bankcards.dto.request.user.UserRegisterDtoRequest;
import com.example.bankcards.entity.User;
import com.example.bankcards.mapper.user.UserRegisterDtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterDtoRequestMapperImpl implements UserRegisterDtoRequestMapper {
    @Override
    public User useRegisterDtoRequestToUser(UserRegisterDtoRequest userRegisterDtoRequest) {
        if (userRegisterDtoRequest == null) {
            return null;
        }

        User user = new User();

        user.setFirstname(userRegisterDtoRequest.getFirstname());
        user.setLastname(userRegisterDtoRequest.getLastname());
        user.setLogin(userRegisterDtoRequest.getLogin());

        return user;
    }
}
