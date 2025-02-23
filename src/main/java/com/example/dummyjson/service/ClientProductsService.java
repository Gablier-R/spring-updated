package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "productsClient", url = "${baseurl.dummyJson}")
public interface ClientProductsService {

    @GetMapping("/")
    Optional<ResponseEntity<ResponseDTO>> getAllProducts();

    @GetMapping("/{id}")
    Optional<ResponseEntity<Product>> getProductById(@PathVariable("id") Long idProduct);
}
