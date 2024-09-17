
package com.example.CarRentals.Controllers;

import com.example.CarRentals.Services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.CarRentals.Exceptions.IncorrectPasswordException;
import com.example.CarRentals.Exceptions.UsernameNotFoundException;
import com.example.CarRentals.Models.Database.User;
import com.example.CarRentals.Models.Request.LoginRequest;
import com.example.CarRentals.Models.Request.SignUpRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "API for managing users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user", description = "Sign up a new user")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
        logger.info("Received sign-up request for email: {}", signUpRequest.getEmail());
        User savedUser = userService.signUp(signUpRequest);
        logger.info("User signed up successfully with id: {}", savedUser.getId());
        return ResponseEntity.ok(savedUser);

    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Login a user")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            userService.login(loginRequest);
            return ResponseEntity.ok("User logged in successfully");
        } catch (UsernameNotFoundException e) {
            logger.error("User not found for email: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for email: " + loginRequest.getEmail());
        } catch (IncorrectPasswordException e) {
            logger.error("Invalid password for user: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password for user: " + loginRequest.getEmail());
        }
    }
}
