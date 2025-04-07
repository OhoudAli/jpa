package com.example.capstone1_jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "name should not be emtpy")
    @Size(min = 3,message = "name length should be more than 3")
    private String name;

    @Column(columnDefinition = "int not null")
    @Positive(message = "price must be positive")
    @NotNull(message = "price should not be empty")
    private int price;

    @NotEmpty(message = "should not be empty")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @Column(columnDefinition = "int not null")
    private int rating;

}
