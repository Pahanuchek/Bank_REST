package com.example.bankcards.mapper.user;

import com.example.bankcards.dto.request.user.UserRegisterDtoRequest;
import com.example.bankcards.entity.User;

public interface UserRegisterDtoRequestMapper {

    User useRegisterDtoRequestToUser(UserRegisterDtoRequest userRegisterDtoRequest);
}
