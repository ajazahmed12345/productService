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
//    @DisplayName("1 + 1 = 2")
//    void onePlusOneEqualsTwo() throws NotFoundException {
////        Random r = new Random();
////        assert(100 < 900);
//
////        assertTrue(50 < 6, "not true");
//
//        assertThrows(NotFoundException.class, ()->fakeStoreProductServiceClient.getProductById(101L));
//
//    }
//
//    @Test
//    void testCorrectSum() {
//        assertEquals(5, 2 + 3, "not correct because 2+1 != 5");
//    }

}
