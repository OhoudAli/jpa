package com.example.capstone1_jpa.Controller;


import com.example.capstone1_jpa.Api.ApiResponse;
import com.example.capstone1_jpa.Model.Merchant;
import com.example.capstone1_jpa.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchant(){

        return ResponseEntity.status(200).body(merchantService.getAllMerchant());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("merchant added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = merchantService.updateMerchant(id, merchant);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse(" merchant updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("api not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id){

        merchantService.deleteMerchant(id);
        return ResponseEntity.status(200).body(new ApiResponse("merchant deleted successfully"));
    }



}
