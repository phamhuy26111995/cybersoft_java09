package com.hto.admin.controller;

import com.hto.admin.dto.CategoryRequestDTO;
import com.hto.admin.entity.CategoryEntity;
import com.hto.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-list")
    public ResponseEntity<List<CategoryEntity>> getListCategory() {

        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Long> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {

        return new ResponseEntity<>(categoryService.createCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Long> updateCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {

        return new ResponseEntity<>(categoryService.updateCategory(categoryRequestDTO), HttpStatus.OK);
    }
}
