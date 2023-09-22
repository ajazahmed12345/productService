package com.ajaz.productservice.services;

import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategoryById(String uuid) throws NotFoundException{
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category not found");
        }

        Category category = categoryOptional.get();

//        List<Product> products = category.getProducts();

        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

//    @Override
//    public List<Product> getProductsByACategory(String categoryName) {
//        return null;
//    }
    @Override
    public List<Product> getProductsByACategory(String categoryName) {

        List<Category> categories = categoryRepository.findAllByName(categoryName);

        List<Product> products = new ArrayList<>();

        for(Category category : categories){
            products.addAll(
                    category.getProducts()
            );
        }

        return products;
    }


}
