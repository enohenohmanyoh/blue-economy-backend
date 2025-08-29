package com.blue_economy.service;

import com.blue_economy.exception.CustomerException;
import com.blue_economy.model.User;
import com.blue_economy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> updateUserById(User user, Long id) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setUsername(user.getUsername());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setGender(user.getGender());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomerException("User not found with ID: " + id));
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomerException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public void checkUserActive(User user) {
        if (!user.isActive()) {
            throw new CustomerException("Customer account is inactive");
        }
    }
}
