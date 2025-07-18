package com.example.bankcards.security;

import com.example.bankcards.entity.User;
import com.example.bankcards.exception.services.ServiceException;
import com.example.bankcards.exception.services.enums.ServiceExceptionCode;
import com.example.bankcards.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.USER_NOT_FOUND));

        return new CustomUserDetails(user);
    }
}
