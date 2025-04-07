package com.example.capstone1_jpa.Model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty(message = "id must not be empty")
    private String id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "user name must not be empty")
    @Size(min = 5,message = "must be more than 5")
    private String userName;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "password must not be empty")
    @Size(message = "password must be more than 6")
    private String password;


    @Column(columnDefinition = "varchar(20) not null")
    @Email
    private String email;


    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "role must not be empty")
    @Pattern(regexp = "Admin|Customer")
    private String role;

    @Column(columnDefinition = "double not null")
    @NotNull(message = "balance must not be empty")
    @Positive(message = "balance must be positive")
    private double balance;
}
