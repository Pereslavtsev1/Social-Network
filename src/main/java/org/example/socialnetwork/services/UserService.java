package org.example.socialnetwork.services;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.ApplicationUserResponse;
import org.example.socialnetwork.dtos.RegistrationRequest;
import org.example.socialnetwork.dtos.UpdatePhoneRequest;
import org.example.socialnetwork.dtos.UserNameRequest;
import org.example.socialnetwork.exceptions.EmailAlreadyExistException;
import org.example.socialnetwork.exceptions.EmailFailedToSendException;
import org.example.socialnetwork.exceptions.UserNotFoundException;
import org.example.socialnetwork.mappers.UserMapper;
import org.example.socialnetwork.models.ApplicationUser;
import org.example.socialnetwork.models.Role;
import org.example.socialnetwork.repositories.RoleRepository;
import org.example.socialnetwork.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final MailService mailService;


    public ApplicationUserResponse registerUser(RegistrationRequest userRequest) {
        var user = userMapper.toApplicationUser(userRequest);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException(user.getEmail());
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
        var num = (int) Math.floor(Math.random() * 100_000_000);
        return "@" + lastname + num;

    }

    public ApplicationUserResponse updatePhoneNumber(UpdatePhoneRequest request) {
        ApplicationUser applicationUser = userRepository.findByUsername(request.username()).orElseThrow(UserNotFoundException::new);
        applicationUser.setPhoneNumber(request.phone());
        userRepository.save(applicationUser);
        return null;
    }

    public ApplicationUserResponse getUserByUsername(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return userMapper.toApplicationUserResponse(user);
    }

    public void sentVerificationCode(UserNameRequest request) {
        ApplicationUser applicationUser = userRepository.findByUsername(request.username()).orElseThrow(UserNotFoundException::new);
        applicationUser.setVerificationCode(generateVerificationCode());
        try {
            mailService.createEmail(applicationUser.getEmail(), "Your verification code", "The your verification code: " + applicationUser.getVerificationCode());
            userRepository.save(applicationUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailFailedToSendException();
        }
    }

    private Long generateVerificationCode() {
        return (long) Math.floor(Math.random() * 100_000_000);
    }
}
