package com.blue_economy.controller;

import com.blue_economy.model.User;
import com.blue_economy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Get user by ID
    @GetMapping("/get/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id); // throws CustomerException if not found
        return ResponseEntity.ok(user);
    }

    // Update user by ID
    @PutMapping("/update/user/{id}")
    public ResponseEntity<User> updateById(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.updateUserById(user, id)
                .orElseThrow(() -> new com.blue_economy.exception.CustomerException("User not found with ID: " + id));
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by ID
    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id); // throws CustomerException if not found
        return ResponseEntity.noContent().build();
    }
}
