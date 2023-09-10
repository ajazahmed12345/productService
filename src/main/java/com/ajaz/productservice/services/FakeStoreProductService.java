package com.ajaz.productservice.services;

import com.ajaz.productservice.thirdPartyClients.productService.fakeStoreClient.FakeStoreProductDto;
import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.thirdPartyClients.productService.fakeStoreClient.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{


    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }


    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.getProductById(id));
    }

    public GenericProductDto convertFakeStoreProductToGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());

        return product;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> list = fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto> ans = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : list){
            ans.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDto));
        }
        return ans;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.updateProductById(id, fakeStoreProductDto));
    }
}
