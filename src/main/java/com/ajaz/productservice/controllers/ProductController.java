package com.ajaz.productservice.controllers;

import com.ajaz.productservice.dtos.CategoryDto;
import com.ajaz.productservice.dtos.ExceptionDto;
import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.dtos.ProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.security.JwsTokenObj;
import com.ajaz.productservice.security.TokenValidator;
import com.ajaz.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;
    private TokenValidator tokenValidator;

    public ProductController(ProductService productService, TokenValidator tokenValidator)
    {
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }


    @GetMapping("/getProductById/{userId}/{id}")
    public ProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("userId") Long userId, @PathVariable("id") UUID id) throws NotFoundException{

        System.out.println(authToken);

        JwsTokenObj jwsObjTokenOptional = tokenValidator.validateToken(userId, authToken);


//        JwsTokenObj jwsTokenObj = null;


        if(authToken != null){
            if(jwsObjTokenOptional == null){
                return null;
            }




        }

        return convertProductToProductDto(productService.getProductById(id, jwsObjTokenOptional.getUserId()));
    }

    public ProductDto convertProductToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getUuid());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(convertCategoryToCategoryDto(product.getCategory()));
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());

        return productDto;
    }

    public Product convertProductDtoToProduct(ProductDto productDto){
        Product product = new Product();

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setCategory(convertCategoryDtoToCategory(productDto.getCategory()));
        product.setPrice(productDto.getPrice());

        return product;
    }

    public CategoryDto convertCategoryToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getUuid());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    public Category convertCategoryDtoToCategory(CategoryDto categoryDto){
        Category category = new Category();

        category.setUuid(categoryDto.getId());
        category.setName(categoryDto.getName());

        return category;
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
    public ProductDto createProduct(@RequestBody ProductDto product){
//        return productService;
        System.out.println(product.getCategory().getName());
        Product temp = productService.createProduct(convertProductDtoToProduct(product));
        return convertProductToProductDto(temp);
    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") UUID id){
        try {
            productService.deleteProductById(id);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(
                    "Product not found in the Database!!!",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                "Deleted product with " + id + " successfully",
                HttpStatus.OK
        );
    }
//
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable("id") UUID id, @RequestBody Product product) throws NotFoundException {
        ProductDto productDto = convertProductToProductDto(productService.updateProductById(id, product));
//
//        try {
//            productDto = convertProductToProductDto(productService.updateProductById(id, product));
//        }catch(NotFoundException e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>("Updated the product with id: " + id, HttpStatus.OK);

    }

    @GetMapping("/{title}/{price}")
    public ProductDto getProductByTitleAndPrice(@PathVariable("title") String title,
                                             @PathVariable("price") double price){
        Product product = productService.getProductByTitleAndPrice(title, price);
        ProductDto ans = convertProductToProductDto(product);
        return ans;
    }

//    public Product convertProductDtoToProduct(ProductDto product){
//        Product ans = new Product();
//        ans.setUuid(product.getId());
//        ans.setDescription(product.getDescription());
//
//        ans.setCategory(convertCategoryDtoToCategory(product.getCategory()));
//        ans.setImage(product.getImage());
//        ans.setPrice(product.getPrice());
//        ans.setTitle(product.getTitle());
//
//       Product product1 = productRepository.save(ans);
//
//        return ans;
//    }

//    @GetMapping("/categories/{categoryName}")
//    public List<ProductDto> getProductsByACategory(@PathVariable("categoryName") String categoryName){
//        List<Product> products = productService.getProductsByACategory(categoryName);
//
//        List<ProductDto> productDtos = new ArrayList<>();
//
//        for(Product product :  products){
//            ProductDto productDto = new ProductDto();
//            productDto.setTitle(product.getTitle());
//            productDto.setDescription(product.getDescription());
//            productDto.setImage(product.getImage());
//            productDto.setPrice(product.getPrice());
//            productDto.setCategory(convertCategoryToCategoryDto(product.getCategory()));
//        }
//        return productDtos;
//    }



}
