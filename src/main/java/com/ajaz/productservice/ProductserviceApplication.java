package com.ajaz.productservice;

import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Price;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.repositories.CategoryRepository;
import com.ajaz.productservice.repositories.PriceRepository;
import com.ajaz.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ProductserviceApplication(ProductRepository productRepository,
                                     CategoryRepository categoryRepository,
                                     PriceRepository priceRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setName("herbal products");
//        Category savedCategory = categoryRepository.save(category);

        Price price = new Price();
        price.setPrice(3000);

//        Price savedPrice = priceRepository.save(price);

        Product product = new Product();
        product.setTitle("Himalaya neem face wash");

        product.setDescription("best ever");

        product.setCategory(category);
        product.setPrice(price);

        productRepository.save(product);

//        productRepository.deleteById();

       System.out.println("mai chal rha!!!");
    }


    // Product -> 9166
    // Cat -> d30
    // Price -> a53
}
