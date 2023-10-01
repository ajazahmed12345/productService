package com.ajaz.productservice.services;

import com.ajaz.productservice.dtos.CategoryDto;
import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.dtos.ProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Price;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.repositories.CategoryRepository;
import com.ajaz.productservice.repositories.PriceRepository;
import com.ajaz.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository,
                                  PriceRepository priceRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }
    @Override
    public Product getProductById(UUID id) throws NotFoundException{
        Optional<Product> productOptional = productRepository.findById(id);


        if(productOptional.isEmpty()){
            throw new NotFoundException("Product: " + id + " not found.");
        }

        printProduct(productOptional.get());

        return productOptional.get();


//        return null;
    }

    public void printProduct(Product product){
        System.out.println(product.getCategory().getUuid());
        System.out.println(product.getPrice().getCurrency());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
//        return null;
    }
//


    // modification needed
    @Override
    public Product createProduct(Product product) {
        Product ans = new Product();
        ans.setTitle(product.getTitle());
        ans.setDescription(product.getDescription());
        ans.setImage(product.getImage());
        ans.setPrice(product.getPrice());

        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if(categoryOptional.isPresent()){
            ans.setCategory(categoryOptional.get());
        }
        else{
            Category category = new Category();
            category.setName(product.getCategory().getName());
            ans.setCategory(category);
        }



        return productRepository.save(ans);
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
//        Product product1 = productRepository.save(ans);
//
//        return product1;
//    }

    public Category convertCategoryDtoToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());

        return category;
    }
//
    @Override
    public void deleteProductById(UUID id) throws NotFoundException{
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product to be deleted not found");
        }

        productRepository.deleteById(id);
    }
//
    @Override
    public Product updateProductById(UUID id, Product product) throws NotFoundException{

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
//            Product currentProduct = product;
//            return productRepository.save(currentProduct);

            throw new NotFoundException("Product that you want to update with id: " + id + " does not exist.");
            // throw an exception saying that product that you want to update does not exist
        }

        Product ans = productOptional.get();
        ans.setImage(product.getImage());
        ans.setTitle(product.getTitle());
        ans.setDescription(product.getDescription());
//        ans.setPrice()

//      Category category = categoryRepository.findById(ans.get);

//        Category category = new Category();
//        category.setName(product.getCategory());

//        Price price = new Price();
//        price.setPrice(product.getPrice());

        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if(categoryOptional.isPresent()){
            ans.setCategory(categoryOptional.get());
        }
        else{
            Category category = new Category();
            category.setName(product.getCategory().getName());
            ans.setCategory(category);
        }


        ans.setPrice(product.getPrice());

        return productRepository.save(ans);


    }

    @Override
    public Product getProductByTitleAndPrice(String title, double price) {
        return productRepository.findByTitleAndPrice_Price(title, price);
//        return null;
    }


}
