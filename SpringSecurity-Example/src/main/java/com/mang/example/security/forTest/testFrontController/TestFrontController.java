package com.mang.example.security.forTest.testFrontController;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class TestFrontController {

    private final ApplicationContext applicationContext;

    @ResponseBody
    @GetMapping("/front")
    public String[] getAllBeans(){

        String [] allBeans = applicationContext.getBeanDefinitionNames();
        System.out.println(applicationContext.getClass());

        return new String [] {"ok", "hello"};
    }

    @GetMapping("/front2")
    public String getAllBeans2(){

        String [] allBeans = applicationContext.getBeanDefinitionNames();
        System.out.println(applicationContext.getClass());

        return "/user/login";
    }
}
