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
}
