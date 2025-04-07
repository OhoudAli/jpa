package com.example.capstone1_jpa.Service;


import com.example.capstone1_jpa.Model.Product;
import com.example.capstone1_jpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public Boolean updateProduct(Integer id, Product product){
        Product oldProduct = productRepository.getById(id);

        if(oldProduct== null){
            return false;
        }

        oldProduct.setName(product.getName());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setRating(product.getRating());
        oldProduct.setPrice(product.getPrice());

        productRepository.save(oldProduct);
        return true;
    }

    public Boolean deleteProduct(Integer id){
        Product product = productRepository.getById(id);
        if(product == null){
            return false;
        }
        productRepository.delete(product);
        return true;
    }
}
