package com.mang.example.security.forTest.testBindingError;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class TestBindingError {

    @GetMapping("/testBindingError")
    @ResponseBody
    public String test(@ModelAttribute TestBindingDto testBindingDto){

        log.info(testBindingDto.getId1());
        log.info(testBindingDto.getPassword());

        StringBuilder sb = new StringBuilder();
        sb.append(testBindingDto.getId1()).append(" ");
        sb.append(testBindingDto.getPassword());

        return sb.toString();
    }


}
