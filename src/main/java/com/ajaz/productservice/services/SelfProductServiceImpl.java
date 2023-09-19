package com.ajaz.productservice.services;

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

        return productOptional.get();
//        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
//        return null;
    }
//
    @Override
    public Product createProduct(ProductDto product) {
        Product currentProduct = convertProductDtoToProduct(product);
        return productRepository.save(currentProduct);
    }

    public Product convertProductDtoToProduct(ProductDto product){
        Product ans = new Product();
        ans.setUuid(product.getId());
        ans.setDescription(product.getDescription());

        Category category = new Category();
        category.setName(product.getCategory());
        category.setProducts(new ArrayList<>());

        Price price = new Price();
        price.setPrice(product.getPrice());

        ans.setCategory(category);
        ans.setImage(product.getImage());
        ans.setPrice(price);
        ans.setTitle(product.getTitle());

        Product product1 = productRepository.save(ans);

        return product1;
    }
//
//    @Override
//    public GenericProductDto deleteProductById(Long id) {
//        return null;
//    }
//
    @Override
    public Product updateProductById(UUID id, ProductDto product){

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            Product currentProduct = convertProductDtoToProduct(product);
            return productRepository.save(currentProduct);
        }

        Product ans = productOptional.get();
        ans.setImage(product.getImage());
        ans.setTitle(product.getTitle());
        ans.setDescription(product.getDescription());

//      Category category = categoryRepository.findById(ans.get);

        Category category = new Category();
        category.setName(product.getCategory());

        Price price = new Price();
        price.setPrice(product.getPrice());

        ans.setCategory(category);
        ans.setPrice(price);

        Product savedProduct = productRepository.save(ans);

        return savedProduct;

//        return null;
    }


}
