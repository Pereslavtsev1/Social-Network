package org.example.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.*;
import org.example.socialnetwork.exception.EmailAlreadyExistException;
import org.example.socialnetwork.exception.IncorrectEmailVerificationCode;
import org.example.socialnetwork.mapper.UserMapper;
import org.example.socialnetwork.model.ApplicationUser;
import org.example.socialnetwork.repository.RoleRepository;
import org.example.socialnetwork.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final UserMapper userMapper;
    private static final String USER_ROLE = "USER";
    private static final String EMAIL_SUBJECT = "Your verification code";
    private static final String EMAIL_BODY = "Your verification code: ";

    public ApplicationUserResponse registerUser(ApplicationUserRequest applicationUserRequest) throws EmailAlreadyExistException {
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

    public ApplicationUserResponse updatePhoneNumber(UpdatePhoneNumberRequest phoneNumberRequest) {
        ApplicationUser applicationUser = findUserByUsername(phoneNumberRequest.username());
        applicationUser.setPhone(phoneNumberRequest.phone());
        return userMapper.toApplicationUserResponse(userRepository.save(applicationUser));
    }

    public void sendEmailVerificationCode(EmailRequest emailRequest) {
        ApplicationUser applicationUser = findUserByUsername(emailRequest.username());
        applicationUser.setVerificationCode(generateVerificationCode());
        userRepository.save(applicationUser);
        mailService.sendEmail(applicationUser.getEmail(), EMAIL_SUBJECT, EMAIL_BODY + applicationUser.getVerificationCode());
    }

    private int generateVerificationCode() {
        return (int) (Math.random() * 100_000_000);
    }

    public void verifyEmail(EmailVerifyRequest emailVerifyRequest) {
        ApplicationUser applicationUser = findUserByUsername(emailVerifyRequest.username());
        if (applicationUser.getVerificationCode().equals(emailVerifyRequest.verificationCode())) {
            applicationUser.setEnabled(true);
            applicationUser.setVerificationCode(null);
            userRepository.save(applicationUser);
        } else {
            throw new IncorrectEmailVerificationCode();
        }

    }

    private ApplicationUser findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
