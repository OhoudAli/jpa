package com.example.capstone1_jpa.Controller;


import com.example.capstone1_jpa.Api.ApiResponse;
import com.example.capstone1_jpa.Model.Product;
import com.example.capstone1_jpa.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts(){

        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("product added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = productService.updateProduct(id, product);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("api not found"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){

       productService.deleteProduct(id);
        return ResponseEntity.status(200).body(new ApiResponse("delete successfully"));
    }

}
