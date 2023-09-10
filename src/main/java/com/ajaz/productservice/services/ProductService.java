package com.ajaz.productservice.services;

import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.models.Product;
import com.ajaz.productservice.thirdPartyClients.productService.fakeStoreClient.FakeStoreProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);

    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(Long id);
    GenericProductDto updateProductById(Long id, GenericProductDto product);
}
