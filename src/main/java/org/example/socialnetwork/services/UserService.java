package org.example.socialnetwork.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.ApplicationUserResponse;
import org.example.socialnetwork.dtos.RegistrationRequest;
import org.example.socialnetwork.dtos.UpdatePhoneRequest;
import org.example.socialnetwork.exceptions.EmailAlreadyExistException;
import org.example.socialnetwork.exceptions.UserNotFoundException;
import org.example.socialnetwork.mappers.UserMapper;
import org.example.socialnetwork.models.Role;
import org.example.socialnetwork.repositories.RoleRepository;
import org.example.socialnetwork.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final Random random = new Random();


    public ApplicationUserResponse registerUser(RegistrationRequest userRequest) {
        var user = userMapper.toApplicationUser(userRequest);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException(String.format("User with provided email: %s already exist", user.getEmail()), HttpStatus.BAD_REQUEST);
        }
        boolean nameEmpty = true;
        while (nameEmpty) {
            var temp = generateUsername(userRequest.lastName());
            if (userRepository.findByUsername(temp).isEmpty()) {
                user.setUsername(temp);
                nameEmpty = false;
            }
        }
        Set<Role> authorities = user.getAuthorities();
        authorities.add(roleRepository.findByAuthority("USER").orElseThrow());
        userRepository.save(user);
        return null;
    }

    private String generateUsername(String lastname) {
        var num = random.nextInt(0, 10_000_000);
        return "@" + lastname + num;

    }

    public ApplicationUserResponse updatePhoneNumber(UpdatePhoneRequest request) {
        var user = userRepository.findByUsername(request.username()).orElseThrow(() -> new UserNotFoundException(String.format("User with provided username: %s not found", request.username()), HttpStatus.NOT_FOUND));
        user.setPhoneNumber(request.phone());
        userRepository.save(user);
        return null;
    }

    public ApplicationUserResponse getUserByUsername(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(String.format("User with provided username: %s not found", username), HttpStatus.NOT_FOUND));
        return userMapper.toApplicationUserResponse(user);
    }
}
