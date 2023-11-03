package com.mang.example.security.forTest.testErrorPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//에러 페이지를 출력할 컨트롤러
@Controller
@RequestMapping("/errorPage")
public class ErrorController {

    @GetMapping("/404")
    public String error404(){

        return "error/404";
    }

    @GetMapping("/500")
    public String error500(){

        return "error/500";
    }

    @GetMapping("/runtime")
    public String errorRuntime(){

        return "error/runtime";
    }
}
