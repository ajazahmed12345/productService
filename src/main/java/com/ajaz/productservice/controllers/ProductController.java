package com.ajaz.productservice.controllers;

import com.ajaz.productservice.dtos.ExceptionDto;
import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException{
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){

        return productService.getAllProducts();
    }

    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
//        return productService;
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product){
        return new ResponseEntity<>(
                productService.updateProductById(id, product),
                HttpStatus.CREATED
        );
    }



}
