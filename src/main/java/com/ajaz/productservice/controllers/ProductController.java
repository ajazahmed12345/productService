package com.ajaz.productservice.controllers;

import com.ajaz.productservice.dtos.ExceptionDto;
import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.dtos.ProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException{
        return convertProductToProductDto(productService.getProductById(id));
    }

    public ProductDto convertProductToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getUuid());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(productDto.getImage());
        productDto.setPrice(product.getPrice().getPrice());
        productDto.setTitle(product.getTitle());

        return productDto;
    }

    @GetMapping()
    public List<ProductDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> ans = new ArrayList<>();

        for(Product p : products){
            ans.add(convertProductToProductDto(p));
        }
        return ans;
    }
//
    @PostMapping()
    public Product createProduct(@RequestBody ProductDto product){
//        return productService;
        return productService.createProduct(product);
    }
//
//    @DeleteMapping("/{id}")
//    public GenericProductDto deleteProductById(@PathVariable("id") Long id){
//        return productService.deleteProductById(id);
//    }
//
    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable("id") UUID id, @RequestBody ProductDto product){

            return productService.updateProductById(id, product);

    }



}
