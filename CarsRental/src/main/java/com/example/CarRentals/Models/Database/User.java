package com.example.CarRentals.Models.Database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // Change this import
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "users")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    // Email is used for login, must be unique
    // Email is also used to identify the user in the system
    // Validation added to ensure email is in a valid format
    @Column(name = "email", unique = true, nullable = false)
    @jakarta.validation.constraints.Email(message = "Invalid email format")
    @jakarta.validation.constraints.NotBlank(message = "Email cannot be blank")
    private String email;

    @Column(name = "password", nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Password cannot be blank")
    private String password;
    
    @Column(name = "driver_license_number", unique = true, nullable = false)
    private String driverLicenseNumber;

    public User(String name, String email, String password, String driverLicenseNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.driverLicenseNumber = driverLicenseNumber;
    }
}