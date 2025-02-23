package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.example.dummyjson.Constants.PRODUCT1;
import static com.example.dummyjson.Constants.PRODUCT2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ClientProductsService clientProductsService;

    @Test
    @DisplayName("UNIT: Should return a list of products from the external requisition")
    void testGetAllProducts() {

        ResponseDTO responseDTO = new ResponseDTO(List.of(PRODUCT1, PRODUCT2));

        when(clientProductsService.getAllProducts())
                .thenReturn(Optional.of(ResponseEntity.ok(responseDTO)));

        List<Product> sut = productService.getAllProducts();
        System.out.println("SUT: "+sut);

        assertNotNull(sut);
        assertEquals(2, sut.size());
        assertEquals("Product 1", sut.get(0).title());
    }

    @Test
    @DisplayName("UNIT: Should return a single product by the external request id")
    void testGetProductById() {

        when(clientProductsService.getProductById(1L))
                .thenReturn(Optional.of(ResponseEntity.ok(PRODUCT1)));

        Product sut = productService.getProductById(1L);
        System.out.println("SUT: "+sut);

        assertNotNull(sut);
        assertEquals(1L, sut.id());
        assertEquals("Product 1", sut.title());
    }
}
