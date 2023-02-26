package com.kreuterkeule.authvuejsapp.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kreuterkeule.authvuejsapp.data.UserRepo;
import com.kreuterkeule.authvuejsapp.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

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

    record LoginRequest(String email, String password) {}
    record LoginResponse(String token) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        var login = authService.login(loginRequest.email, loginRequest.password);

        Cookie cookie = new Cookie("refresh_token", login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");

        response.addCookie(cookie);

        return new LoginResponse(login.getAccessToken().getToken());

    }
}