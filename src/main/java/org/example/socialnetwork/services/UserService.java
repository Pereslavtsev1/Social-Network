package org.example.socialnetwork.services;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.repositories.RoleRepository;
import org.example.socialnetwork.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
}
