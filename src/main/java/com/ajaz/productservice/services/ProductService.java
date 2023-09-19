package com.ajaz.productservice.services;

import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.dtos.ProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.thirdPartyClients.productService.fakeStoreClient.FakeStoreProductDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID id) throws NotFoundException;
    Product createProduct(ProductDto product);
//
    List<Product> getAllProducts();
//    GenericProductDto deleteProductById(Long id);
    Product updateProductById(UUID id, ProductDto product);
}
