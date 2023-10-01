package com.ajaz.productservice.thirdPartyClients.productService.fakeStoreClient;

import com.ajaz.productservice.dtos.GenericProductDto;
import com.ajaz.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient{

    private RestTemplateBuilder restTemplateBuilder;



    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private String productRequestsBaseUrl; //= fakeStoreApiUrl + fakeStoreProductsApiPath;
    private String specificProductRequestUrl; //= fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestsBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
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

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("product with id: " + id + " NOT found");
//            return null;
        }

        return fakeStoreProductDto;

    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);

        List<FakeStoreProductDto> ans = new ArrayList<>();


        for(FakeStoreProductDto fakeStoreProductDto : Arrays.stream(response.getBody()).toList()){
           ans.add(fakeStoreProductDto);
        }

        return ans;
    }


    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestsBaseUrl, product, FakeStoreProductDto.class);
//        System.out.println(response.getHeaders());
        return response.getBody();
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

//        System.out.println(response.getStatusCode());

        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProductById(Long id, FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(specificProductRequestUrl, product, id);


        return product;
    }

}
