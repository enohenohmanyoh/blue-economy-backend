package com.blue_economy.controller;


import com.blue_economy.dto.AuthRequest;

import com.blue_economy.dto.DtoRequest;
import com.blue_economy.model.User;
import com.blue_economy.repository.UserRepository;
import com.blue_economy.service.JwtUtil;
import com.blue_economy.service.PasswordResetService;
import com.blue_economy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;
    @Autowired
    private PasswordResetService passwordResetService;




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );

            User user = userRepository.findByEmail(req.getEmail()).get();

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "id", user.getId(),
                    "firstName", user.getFirstName(),
                    "lastName", user.getLastName(),
                    "email", user.getEmail(),
                    "role", user.getRole()
            ));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }
    }


    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody DtoRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already registered"));
        }

        User admin = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .username(req.getFirstName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .birthDate(req.getBirthDate())
                .gender(req.getGender())
                .role("ROLE_ADMIN")  // 👈 mark as admin
                .build();

        userRepository.save(admin);
        return ResponseEntity.ok(Map.of("message", "Admin registered successfully"));
    }


    @PostMapping("/register/user")
    public ResponseEntity<?> register(@RequestBody DtoRequest req) {
        System.out.println(req);
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already registered"));
        }

        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .username(req.getFirstName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .birthDate(req.getBirthDate())
                .gender(req.getGender())
                .role("ROLE_USER") // Public registration only USER
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Registered successfully"));


    }




    // RESETTING PASSWORD
    @PostMapping("/forgot/password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        passwordResetService.createPasswordResetToken(email);
        return ResponseEntity.ok("Password reset email sent");
    }

    @PostMapping("/reset/password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("password");
        passwordResetService.resetPassword(token, newPassword, passwordEncoder);
        return ResponseEntity.ok("Password reset successful");
    }

}







