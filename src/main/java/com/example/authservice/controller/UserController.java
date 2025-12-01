package com.example.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @GetMapping("/profile")
    public String getProfile() {
        return "This is user profile";
    }
}
