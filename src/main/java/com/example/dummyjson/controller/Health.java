package com.example.dummyjson.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${baseurl.path}")
public class Health {

    @GetMapping("health")
    ResponseEntity<String> healthCheck (){
        return new ResponseEntity<>("Service run", HttpStatus.OK);
    }
}
