package com.example.dummyjson.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.dummyjson.Constants.PRODUCT1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductTest {

    @Test
    @DisplayName("UNIT: Should validate the get method of the product object")
    public void testGet(){

        Long expectId = 1L;
        String expectedTitle = "Product 1";
        String expectedDescription = "Description 1";
        Double expectedPrice = 10.0;

        assertEquals(expectId, PRODUCT1.id());
        assertEquals(expectedTitle, PRODUCT1.title());
        assertEquals(expectedDescription, PRODUCT1.description());
        assertEquals(expectedPrice, PRODUCT1.price());
    }

    @Test
    @DisplayName("UNIT: Should return a new product object")
    public void testConstructorFull(){

        Product expected = new Product(1L, "Product 1", "Description 1", 10.0);

        assertEquals(expected.id(), PRODUCT1.id());
        assertEquals(expected.title(), PRODUCT1.title());
        assertEquals(expected.description(), PRODUCT1.description());
        assertEquals(expected.price(), PRODUCT1.price());
    }
}