package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.example.dummyjson.Constants.PRODUCT1;
import static com.example.dummyjson.Constants.PRODUCT2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientProductsServiceTest {

    @MockBean
    private ClientProductsService productClient;

    @Test
    @DisplayName("Unit: Should return a list of products")
    void testGetAllProducts() {
        ResponseDTO responseDTO = new ResponseDTO(List.of(PRODUCT1, PRODUCT2));
        ResponseEntity<ResponseDTO> mockResponse = new ResponseEntity<>(responseDTO, HttpStatus.OK);

        when(productClient.getAllProducts()).thenReturn(Optional.of(mockResponse));

        Optional<ResponseEntity<ResponseDTO>> result = productClient.getAllProducts();

        assertTrue(result.isPresent());
        assertEquals(HttpStatus.OK, result.get().getStatusCode());
        assertNotNull(result.get().getBody());
        assertEquals(2, result.get().getBody().products().size());
    }

    @Test
    @DisplayName("Unit: Should return a single product by ID")
    void testGetProductById() {
        ResponseEntity<Product> mockResponse = new ResponseEntity<>(PRODUCT1, HttpStatus.OK);

        when(productClient.getProductById(1L)).thenReturn(Optional.of(mockResponse));

        Optional<ResponseEntity<Product>> result = productClient.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals(HttpStatus.OK, result.get().getStatusCode());
        assertNotNull(result.get().getBody());
        assertEquals(1L, result.get().getBody().id());
        assertEquals("Product 1", result.get().getBody().title());
    }
}
