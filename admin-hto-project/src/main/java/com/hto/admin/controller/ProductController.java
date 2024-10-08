package com.hto.admin.controller;

import com.hto.admin.consts.Consts;
import com.hto.admin.dto.ProductDTO;
import com.hto.admin.dto.ProductRequestDTO;
import com.hto.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {

        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable long id) {

        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ProductRequestDTO requestDTO) {
        productService.save(requestDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ProductRequestDTO requestDTO) {
        productService.update(requestDTO);

        return new ResponseEntity(HttpStatus.OK);


    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ProductRequestDTO requestDTO) {

        productService.delete(requestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

}
