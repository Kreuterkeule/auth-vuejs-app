package com.kreuterkeule.authvuejsapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api")
public class AuthController {

    private AuthService authService;

    public AuthController(UserRepo userRepo, AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = "/hello")
    public String postHello() {
        return "hello";
    }

    record RegisterRequest(
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            String email,
            String password,
            @JsonProperty("password_confirm") String passwordConfirm) {}
    record RegisterResponse(
            Long id,
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            String email) {}

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {

        var user = authService.register(
                registerRequest.firstName,
                registerRequest.lastName,
                registerRequest.email,
                registerRequest.password,
                registerRequest.passwordConfirm
        );

        return new RegisterResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}