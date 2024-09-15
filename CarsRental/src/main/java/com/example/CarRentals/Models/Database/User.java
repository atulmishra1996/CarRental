package com.example.CarRentals.Models.Database;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private String name;
    private String email;
    private String password;
    private String driverLicenseNumber;
}
