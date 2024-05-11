package com.mang.example.security.forTest.testSpringSecurityRedirect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/spring-security-redirect")
@Slf4j
public class SpringSecurityRedirect {

    @GetMapping("/start")
    public String start() {

        return "user/spring-security-redirect";
    }

    @PostMapping("/postRequest")
    public ResponseEntity<String> post(@RequestBody Map<String, String> data)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(data);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/postRequest")
    public String get() {

        log.info("GET 호출");
        return "redirect:/spring-security-redirect/start";
    }

}
