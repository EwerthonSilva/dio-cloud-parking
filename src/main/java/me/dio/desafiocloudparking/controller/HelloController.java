package me.dio.desafiocloudparking.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping
    @Operation(hidden = true)
    public String hello(){
        return "Hello Word for nós";
    }
}



