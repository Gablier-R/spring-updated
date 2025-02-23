package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static com.example.dummyjson.Constants.PRODUCT1;
import static com.example.dummyjson.Constants.PRODUCT2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTestUNIT {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    @DisplayName("UNIT: Should return a list of products")
    public void testGetAllProducts() {

        List<Product> products = Arrays.asList(PRODUCT1, PRODUCT2);
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> sut = productController.getAllProducts();
        System.out.println("SUT: "+sut.getBody());

        assertEquals(2, sut.getBody().size());
        assertEquals(1L, sut.getBody().get(0).id());
        assertEquals(2L, sut.getBody().get(1).id());
        assertEquals("Product 1", sut.getBody().get(0).title());
        assertEquals("Product 2", sut.getBody().get(1).title());
    }

    @Test
    @DisplayName("UNIT: Should return a single product by Id")
    public void testGetProductById() {
        when(productService.getProductById(1L)).thenReturn(PRODUCT1);

        ResponseEntity<Product> sut = productController.getProductById(1L);
        System.out.println("SUT: "+sut.getBody());

        assertNotNull(sut.getBody());
        assertEquals(1L, sut.getBody().id());
        assertEquals("Product 1", sut.getBody().title());
        assertEquals("Description 1", sut.getBody().description());
        assertEquals(10.0, sut.getBody().price());
    }
}
