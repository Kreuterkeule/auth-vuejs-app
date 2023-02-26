package com.kreuterkeule.authvuejsapp.service;

import com.kreuterkeule.authvuejsapp.constants.SecurityConstants;
import com.kreuterkeule.authvuejsapp.data.UserEntity;
import com.kreuterkeule.authvuejsapp.data.UserRepo;
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

    public Login login(String email, String password) {

        // find user by email
        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "email does not exist in database"));
        // see if the passwords match
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password does not match password in database");

        // return user
        return Login.of(user.getId(), SecurityConstants.JWT_ACCESS_SECRET, SecurityConstants.JWT_REFRESH_SECRET);
    }
}
