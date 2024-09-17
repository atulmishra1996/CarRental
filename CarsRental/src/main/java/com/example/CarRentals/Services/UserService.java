package com.example.CarRentals.Services;
import com.example.CarRentals.Repositories.UserRepository;
import com.example.CarRentals.Controllers.UserController;
import com.example.CarRentals.Exceptions.IncorrectPasswordException;
import com.example.CarRentals.Exceptions.UsernameNotFoundException;
import com.example.CarRentals.Models.Database.User;
import com.example.CarRentals.Models.Request.LoginRequest;
import com.example.CarRentals.Models.Request.SignUpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.el.stream.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
     * Sign up a new user
     * @param signUpRequest
     * Converst signUpRequest to User and saves it to the database
     * @return User 
     */
    public User signUp(SignUpRequest signUpRequest) {
        logger.info("Received sign-up request for email: {}", signUpRequest.toString());
        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getDriverLicenseNumber());

        try {
            User savedUser = userRepository.save(user);
            logger.info("User saved successfully: {}", savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error saving user: ", e);
            throw e;
        }
    }

    public User login(LoginRequest loginRequest) throws UsernameNotFoundException, IncorrectPasswordException {
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());
            if (user == null) {
                logger.error("User not found for email: {}", loginRequest.getEmail());
                throw new UsernameNotFoundException("User not found for email: " + loginRequest.getEmail());
            } else {
                String decodedPassword = passwordEncoder.encode(user.getPassword());
                if (loginRequest.getPassword().equals(decodedPassword)) {
                    return user;
                } else {
                    logger.error("Invalid password for user: {}", user.getEmail());
                    throw new IncorrectPasswordException("Invalid password for user: " + user.getEmail());
                } 
            }
        } catch (Exception e) {
            logger.error("Something went wrong while logging in: ", e);
            throw e;
        }
    }
}
