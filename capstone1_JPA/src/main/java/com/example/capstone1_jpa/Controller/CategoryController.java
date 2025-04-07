package com.example.capstone1_jpa.Controller;


import com.example.capstone1_jpa.Api.ApiResponse;
import com.example.capstone1_jpa.Model.Category;
import com.example.capstone1_jpa.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategory());
    }


    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("category added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = categoryService.updateCategory(id, category);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("category updated "));
        }
        return ResponseEntity.status(400).body(new ApiResponse("api not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){

       categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("category deleted "));
    }



}
