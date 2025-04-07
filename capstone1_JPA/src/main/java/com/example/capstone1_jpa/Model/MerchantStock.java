package com.example.capstone1_jpa.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // Foreign key to Product
    private Product product;

    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false) // Foreign key to Merchant
    private Merchant merchant;

    @NotNull(message = "stock must not be empty")
    @Min(value = 10,message = "Stock most be more than 10 to start ")
    private int stock;
}
