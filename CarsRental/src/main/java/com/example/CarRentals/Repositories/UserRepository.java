package com.example.CarRentals.Repositories;

import org.springframework.stereotype.Repository;
import com.example.CarRentals.Models.Database.User;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
