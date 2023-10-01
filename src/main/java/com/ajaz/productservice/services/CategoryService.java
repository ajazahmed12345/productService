package com.ajaz.productservice.services;

import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Product;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category getCategoryById(String uuid) throws NotFoundException;
    List<Category> getAllCategories();

    List<Product> getProductsByACategory(String categoryName) throws NotFoundException;

    List<String> getProductTitles(List<UUID> uuids);
}
