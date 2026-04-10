package com.ecowaste.ecowaste_tn.controller;

import com.ecowaste.ecowaste_tn.model.User;
import com.ecowaste.ecowaste_tn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            response.put("status", "error");
            response.put("message", "Email already registered");
            return response;
        }
        userRepo.save(user);
        response.put("status", "success");
        response.put("message", "Registration successful");
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();
        String email = body.get("email");
        String password = body.get("password");
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            User user = userOpt.get();
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
        } else {
            response.put("status", "error");
            response.put("message", "Invalid email or password");
        }
        return response;
    }
}