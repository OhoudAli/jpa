package com.example.capstone1_jpa.Model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "name must not be empty")
    @Size(min = 3, message = "name must be more than 3")
    private String name;

    @Column(columnDefinition = "double not null")
    private double rating;

    @Column(columnDefinition = "varchar(20) not null")
    private String topMerchant;

    @Column(columnDefinition = "double not null")
    private double merchantRate;
}
