package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ClientProductsService webClient;

    public List<Product> getAllProducts() {
        return webClient.getAllProducts().get().getBody().products();
    }

    public Product getProductById(Long idProduct) {
        return webClient.getProductById(idProduct).get().getBody();
    }
}
