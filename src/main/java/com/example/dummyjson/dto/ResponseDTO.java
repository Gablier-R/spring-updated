package com.example.dummyjson.dto;

import java.util.List;

public record ResponseDTO(
        List<Product> products
) { }
