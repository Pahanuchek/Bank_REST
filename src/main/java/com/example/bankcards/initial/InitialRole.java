package com.example.bankcards.initial;

import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.enums.ERole;
import com.example.bankcards.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class InitialRole {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void initializeRoles() {
        Arrays.stream(ERole.values()).forEach(this::initializeRole);
    }

    private void initializeRole(ERole roleName) {
        if (!roleRepository.existsByName(roleName)) {
            Role role = new Role(roleName);
            roleRepository.save(role);
        }
    }
}
