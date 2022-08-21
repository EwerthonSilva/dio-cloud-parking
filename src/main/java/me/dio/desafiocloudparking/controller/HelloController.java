package me.dio.desafiocloudparking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping
    public String hello(){
        return "Hello Word for nós";
    }
}
