package com.kreuterkeule.authvuejsapp;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;


    public AuthService(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    public UserEntity register(String firstName, String lastName, String email, String password, String passwordConfirm) {
        if (!Objects.equals(password, passwordConfirm)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "passwords do not match");
        }

        return userRepo.save(
                UserEntity.of(firstName, lastName, email, passwordEncoder.encode(password))
        );
    }
}
