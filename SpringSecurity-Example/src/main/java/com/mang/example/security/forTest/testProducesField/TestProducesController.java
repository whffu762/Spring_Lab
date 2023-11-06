package com.mang.example.security.forTest.testProducesField;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestProducesController {

    @RequestMapping("/testRequest")
    public String getHtml(){
        return "user/login";
    }

    @RequestMapping(value = "/testRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getApi(){
        return new ResponseEntity<>("test Result", HttpStatus.OK);
    }

}
