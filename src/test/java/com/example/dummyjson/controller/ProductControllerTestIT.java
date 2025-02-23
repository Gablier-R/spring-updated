package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.dummyjson.Constants.PRODUCT1;
import static com.example.dummyjson.Constants.PRODUCT2;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yaml")
class ProductControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Value("${baseurl.path}")
    private String baseurl;

    @Test
    @DisplayName("IT: Should return a list of products")
    void testGetAllProducts() throws Exception {

        List<Product> mockProducts = List.of(PRODUCT1, PRODUCT2);

        when(productService.getAllProducts()).thenReturn(mockProducts);

        mockMvc.perform(get( "http://localhost:8080/"+baseurl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Product 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].price").value(10.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Product 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].price").value(20.0));
    }

    @Test
    @DisplayName("IT: Should return a single product by Id")
    void testProductById() throws Exception {

        when(productService.getProductById(any())).thenReturn(PRODUCT1);

        mockMvc.perform(get( "http://localhost:8080/"+baseurl+"/"+1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Product 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));
    }

}