package com.mang.example.security.forTest.testPathVariable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPathVariableController {

    @PostMapping("/test/{id}")
    public String test1(@PathVariable("id") String id){
        return id;
    }

    @GetMapping("/test2/{id}")
    public String test2(@PathVariable("id") String id){
        return id;
    }

    @GetMapping("/test3/{id}")
    public String test3(@PathVariable("id") Integer id){
        return Integer.toString(id);
    }

}
