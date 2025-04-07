package com.example.capstone1_jpa.Controller;


import com.example.capstone1_jpa.Api.ApiResponse;
import com.example.capstone1_jpa.Model.MerchantStock;
import com.example.capstone1_jpa.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stock")
public class MerchantStockController {


    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity getAllStocks(){

        return ResponseEntity.status(200).body(merchantStockService.getStock());
    }


    @PostMapping("/add")
    public ResponseEntity addStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantStockService.addStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("stock added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@PathVariable Integer id, @Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = merchantStockService.updateStock(id, merchantStock);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("stock updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("api not found"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoffee(@PathVariable Integer id){

        merchantStockService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("delete successfully"));
    }

    @PostMapping("/add/{productId}/{merchantId}/{amount}")
    public String addNewStock(@PathVariable Integer productId, @PathVariable Integer merchantId, @PathVariable int amount) {
        return merchantStockService.addNewStock(productId, merchantId, amount);
    }

    @PutMapping("/update/{productId}/{merchantId}/{amount}")
    public String updateStockLevel(@PathVariable Integer productId, @PathVariable Integer merchantId, @PathVariable int amount) {
        boolean isUpdated = merchantStockService.updateStockLevel(productId, merchantId, amount);
        return isUpdated ? "Stock updated successfully" : "Stock not found for this product and merchant";
    }

    @PostMapping("/buy/{productId}/{merchantId}")
    public String buyProduct(@PathVariable Integer productId, @PathVariable Integer merchantId) {
        return merchantStockService.buyProduct(productId, merchantId);
    }

    @GetMapping("/check-stock/{productId}/{merchantId}")
    public String checkStockLevel(@PathVariable Integer productId, @PathVariable Integer merchantId) {
        return merchantStockService.checkStockLevel(productId, merchantId);
    }
}
