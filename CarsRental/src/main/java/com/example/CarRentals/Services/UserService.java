package com.example.CarRentals.Services;
import com.example.CarRentals.Repositories.UserRepository;
import com.example.CarRentals.Models.Database.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(User user) {
        return userRepository.save(user);
    }

    public User login(User user) {
        return userRepository.findByEmail(user.getEmail());
    }
}
