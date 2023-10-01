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

        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByACategory(String categoryName) throws NotFoundException{


        // only one Category object Optional<Category>

        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);

        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category Name not found in the database");
        }

        return categoryOptional.get().getProducts();
    }

    @Override
    public List<String> getProductTitles(List<UUID> uuids) {

        List<Category> categories = categoryRepository.findAllById(uuids);


        List<String> titles = new ArrayList<>();

        categories.forEach(
                category -> {
                    category.getProducts().forEach(
                            product -> titles.add(product.getTitle() + ", " + product.getCategory().getUuid())
                    );
                }
        );


        return titles;
    }
}
