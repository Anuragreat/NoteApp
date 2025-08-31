package com.anurag.notesapp.service;

import com.anurag.notesapp.model.User;
import com.anurag.notesapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public String generateOtp(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return "User not found";

        String otp = String.valueOf(new Random().nextInt(999999));
        User user = userOpt.get();
        user.setOtp(otp);
        userRepository.save(user);

        emailService.sendOtp(email, otp);
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return false;

        User user = userOpt.get();

        // Check if OTP matches
        if (user.getOtp() != null && user.getOtp().equals(otp)) {
            user.setOtp(null); // Clear OTP after successful login
            userRepository.save(user);
            return true;
        }

        return false;
    }



    public User signup(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
