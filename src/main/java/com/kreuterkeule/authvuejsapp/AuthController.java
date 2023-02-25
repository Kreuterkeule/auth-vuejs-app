package com.kreuterkeule.authvuejsapp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AuthController {

    private final UserRepo userRepo;

    public AuthController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(value = "/hello")
    public String postHello() {
        return "hello";
    }

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user) {
        return userRepo.save(user);
    }
}