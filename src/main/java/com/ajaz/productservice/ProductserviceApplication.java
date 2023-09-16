package com.ajaz.productservice;

import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.repositories.CategoryRepository;
import com.ajaz.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductserviceApplication(ProductRepository productRepository,
                                     CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setName("herbal products");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setTitle("Himalaya neem face wash");
        product.setPrice(3000);
        product.setDescription("best ever");
        product.setCategory(savedCategory);

        productRepository.save(product);
//        System.out.println("mai chal rha!!!");
    }
}
