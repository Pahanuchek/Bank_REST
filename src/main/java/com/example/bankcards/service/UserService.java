package com.example.bankcards.service;

import com.example.bankcards.dto.request.user.UserAuthDtoRequest;
import com.example.bankcards.dto.request.user.UserRegisterDtoRequest;
import com.example.bankcards.dto.response.user.UserAuthDtoResponse;
import com.example.bankcards.dto.response.user.UserRegisterDtoResponse;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.enums.ERole;
import com.example.bankcards.exception.services.ServiceException;
import com.example.bankcards.exception.services.enums.ServiceExceptionCode;
import com.example.bankcards.mapper.user.UserRegisterDtoRequestMapper;
import com.example.bankcards.repository.RoleRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final static String USER = "user";
    private final static String ADMIN = "admin";
    private static final String DELIMITER = ", ";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRegisterDtoRequestMapper userRegisterDtoRequestMapper;

    @Transactional
    public UserRegisterDtoResponse userRegister(UserRegisterDtoRequest userDtoRequest) {

        if (userRepository.existsByLogin(userDtoRequest.getLogin())) {
            throw new ServiceException(ServiceExceptionCode.USER_EXIST);
        }
        User user = createUser(userDtoRequest);
        user = userRepository.save(user);

        return new UserRegisterDtoResponse(user.getId(), user.getLogin());
    }

    public UserAuthDtoResponse userAuth(UserAuthDtoRequest userAuthDtoRequest) {

        String login = userAuthDtoRequest.getLogin();

        User user = userRepository.findByLogin(login).orElseThrow(
                () -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND)
        );
        if (!(user.getId() == userAuthDtoRequest.getId() || user.getPassword()
                .equals(passwordEncoder.encode(userAuthDtoRequest.getPassword())))) {
            throw new ServiceException(ServiceExceptionCode.INVALID_USER_DATE);
        }

        String jwtToken = authenticationUserAndGenerateJwtToken(userAuthDtoRequest);

        return new UserAuthDtoResponse(jwtToken, user.getId(), user.getLogin(), user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet()));
    }

    private String authenticationUserAndGenerateJwtToken(UserAuthDtoRequest userAuthDtoRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDtoRequest.getLogin(),
                        userAuthDtoRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.generateJwtToken(authentication);
    }

    public User getUserById(long userId) {
       return userRepository.findById(userId)
               .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));
    }

    private Set<Role> generateRoles(Set<String> stringRoles) {
        Set<Role> roles = new HashSet<>();

        for (String role: stringRoles) {
            if (role.equals(USER)) {
                roles.add(roleRepository.findByName(ERole.ROLE_USER));
            } else if (role.equals(ADMIN)) {
                roles.add(roleRepository.findByName(ERole.ROLE_ADMIN));
            } else {
                throw new ServiceException(ServiceExceptionCode.INVALID_ROLE);
            }
        }
        return roles;
    }

    private User createUser(UserRegisterDtoRequest userDtoRequest) {
        User user = userRegisterDtoRequestMapper.useRegisterDtoRequestToUser(userDtoRequest);
        user.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));
        Set<Role> roles = generateRoles(userDtoRequest.getRoles());
        user.setRoles(roles);
        return user;
    }
}
