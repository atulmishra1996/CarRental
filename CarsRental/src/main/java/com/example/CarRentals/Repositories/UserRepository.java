package com.example.CarRentals.Repositories;

import org.springframework.stereotype.Repository;
import com.example.CarRentals.Models.Database.User;
import java.util.ArrayList;

import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public User save(User user) {
        users.add(user);
        return user;
    }

    public User findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
