package com.mang.example.security.forTest.testMultipart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/test")
public class TestMultipartRequest {

    @GetMapping("/multipart")
    public String multipart(){ return "/test/multipart"; }

    @PostMapping("/upload")
    public String upload(@RequestParam("input")MultipartFile file, Model model) throws IOException {

        log.info("file : {} ", file.getName());
        log.info("file : {} ", file.getOriginalFilename());
        log.info("file : {} ", file.getSize());
        log.info("file : {} ", file.getBytes());
        log.info("file : {} ", file.getInputStream());
        log.info("file : {} ", file.getContentType());
        log.info("file : {} ", file.getResource());

        String base64Image = Base64Utils.encodeToString(file.getBytes());
        model.addAttribute("image", base64Image);
        return "/test/multipart";
    }

    @GetMapping("/input")
    public String test(Model model){

        model.addAttribute("Member", new Member());

        return "test/formData";
    }

    @PostMapping("/input")
    public String testFormData2(@ModelAttribute Member member, Model model){

        log.info("id {}, pw {}", member.id, member.password);

        model.addAttribute("Member", member);

        return "/test/formData";

    }

    @PostMapping("/input2")
    public String testFormData(@RequestParam("id") String id, @RequestParam("password") String pw, Model model){

        log.info("id {}, pw {}", id, pw);

        model.addAttribute("Member", new Member());

        model.addAttribute("id", id);
        model.addAttribute("password", pw);

        return "/test/formData";
    }

}
