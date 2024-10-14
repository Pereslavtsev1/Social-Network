package org.example.socialnetwork.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.ApplicationUserRequest;
import org.example.socialnetwork.dtos.ApplicationUserResponse;
import org.example.socialnetwork.exception.EmailAlreadyExistException;
import org.example.socialnetwork.mapper.UserMapper;
import org.example.socialnetwork.model.ApplicationUser;
import org.example.socialnetwork.repository.RoleRepository;
import org.example.socialnetwork.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private static final String USER_ROLE = "USER";

    public ApplicationUserResponse registerUser(@Valid ApplicationUserRequest applicationUserRequest) throws EmailAlreadyExistException {
        var applicationUser = userMapper.toApplicationUser(applicationUserRequest);
        if (userRepository.findByEmail(applicationUser.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException(applicationUser.getEmail());
        }
        applicationUser.getAuthority().add(roleRepository.findByAuthority(USER_ROLE).get());
        boolean nameTaken = false;
        while (!nameTaken) {
            var username = generateRandomUsername(applicationUser);
            if (userRepository.findByUsername(username).isEmpty()) {
                applicationUser.setUsername(username);
                nameTaken = true;
            }
        }
        return userMapper.toApplicationUserResponse(userRepository.save(applicationUser));
    }

    private String generateRandomUsername(ApplicationUser applicationUser) {
        return "@" + applicationUser.getLastName() + (long) Math.floor(Math.random() * 100_000_000);
    }
}
