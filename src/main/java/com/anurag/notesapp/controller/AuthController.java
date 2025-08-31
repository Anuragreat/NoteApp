package com.anurag.notesapp.controller;

import com.anurag.notesapp.model.User;
import com.anurag.notesapp.security.JwtUtil;
import com.anurag.notesapp.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userService.signup(user);
        return "User registered. Please request OTP to login!";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {
        return userService.generateOtp(email);
    }

    // ONE-TIME LOGIN USING OTP
    @PostMapping("/login")
        public String login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        boolean valid = userService.verifyOtp(email, otp);
        if (!valid) return "Invalid OTP";

        return JwtUtil.generateToken(email);
}

}
