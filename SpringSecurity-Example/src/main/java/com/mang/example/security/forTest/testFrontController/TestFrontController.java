package com.mang.example.security.forTest.testFrontController;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TestFrontController {

    private final ApplicationContext applicationContext;

    @GetMapping("/test/front")
    public String front(){

        return "/test/front";
    }

    @PostMapping(value = "/test/front", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String front2(@RequestParam("inputImage")MultipartFile image, Model model) throws IOException {

        String base64Image = Base64Utils.encodeToString(image.getBytes());
        model.addAttribute("image", base64Image);
        return "/test/front";
    }

    @PostMapping(value = "/test/front", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String front2(@RequestParam("imageName") String name, Model model){

        model.addAttribute("imageName", name);
        return "/test/front";
    }

    @GetMapping(value="/test/front", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> front3() {

        String data = "{\"name\" : \"hello\"}";
        return ResponseEntity.ok().body(data);
    }

    @GetMapping(value="/test/front", params = "data")
    public String front(@RequestParam("data") String data, Model model){

        model.addAttribute("data", data);
        return "/test/front";
    }

    @GetMapping(value="/test/front/{msg}")
    public String front4(@PathVariable("msg") String data, Model model){

        model.addAttribute("data2", data);
        return "/test/front";
    }
}
