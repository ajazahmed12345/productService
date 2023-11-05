package com.ajaz.productservice.controllers;

import com.ajaz.productservice.exceptions.NotFoundException;
import com.ajaz.productservice.thirdPartyClients.productService.fakeStoreClient.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ProductControllerTest {

//    @Autowired
//    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
//    @Test
//    void testOnePlusOneEqualsTrue(){
//        assert(1+1 == 2);
//
//
////        assertThrows(NotFoundException.class, () -> fakeStoreProductServiceClient.getProductById(200L));
//        int[] arr1 = {1, 2, 3};
//        int[] arr2 = {};
//
//        assertArrayEquals(arr1, arr2);
//
//    }

}
