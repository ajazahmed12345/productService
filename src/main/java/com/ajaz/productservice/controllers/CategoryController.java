package com.ajaz.productservice.controllers;

import com.ajaz.productservice.dtos.CategoryDto;
import com.ajaz.productservice.dtos.ProductDto;
import com.ajaz.productservice.dtos.RequestTitlesDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("uuid") String uuid){
        Category category;
        try {
             category = categoryService.getCategoryById(uuid);
        }catch(NotFoundException e){
            System.out.println("Exception aa gya!");
            return new ResponseEntity<>("Category with id: " + uuid + " does not exist.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(convertCategoryToCategoryDto(category), HttpStatus.OK);
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories(){
        List<Category> categories =  categoryService.getAllCategories();

        List<CategoryDto> categoryDtos = categories.stream().map(e -> convertCategoryToCategoryDto(e)).collect(Collectors.toList());
        return categoryDtos;
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsByACategory(@PathVariable("categoryName") String categoryName){

        List<Product> products;

        try {
            products = categoryService.getProductsByACategory(categoryName);
        }catch(NotFoundException e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product :  products){
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getUuid());
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDto.setCategory(convertCategoryToCategoryDto(product.getCategory()));

            productDtos.add(productDto);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }



    public CategoryDto convertCategoryToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getUuid());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    @GetMapping("/titles/")
    public List<String> getProductTitles(@RequestBody RequestTitlesDto request){
        List<UUID> uuids = new ArrayList<>();

        for(String uuid : request.getUuids()){
            uuids.add(UUID.fromString(uuid));
        }

        return categoryService.getProductTitles(uuids);
    }

}
