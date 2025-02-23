package com.example.dummyjson.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.dummyjson.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResponseDTOTest {

    @Test
    @DisplayName("UNIT: Should return a new product object and validate to be get")
    public void testGetAndConstructor(){
        var list = List.of(PRODUCT1, PRODUCT2);

        ResponseDTO expected = new ResponseDTO(list);

        assertEquals(2, expected.products().size());

        assertEquals(expected.products().get(0), PRODUCT1);
        assertEquals(expected.products().get(1), PRODUCT2);
    }
}
