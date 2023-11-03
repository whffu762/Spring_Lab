package com.mang.example.security.forTest.testErrorPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//에러 페이지를 출력할 컨트롤러
@Controller
public class ErrorController {

    @GetMapping("/errorPage/404")
    public String errorPage404(){

        return "errorPage/404";
    }

    @GetMapping("/errorPage/500")
    public String errorPage500(){

        return "errorPage/500";
    }

    @GetMapping("/errorPage/runtime")
    public String errorPageRuntime(){

        return "errorPage/runtime";
    }


    /** Spring 에서 알아서 처리해주는 에러 페이지 **/

    @GetMapping("/error/404")
    public String error404(){
        return "error/404";
    }

    @GetMapping("/error/500")
    public String error500(){
        return "error/500";
    }

    //상태코드로만 예외를 구분하기 때문에 자바에서 던지는 예외는 구분하지 못 함
    @GetMapping("/error/runtime")
    public String errorRuntime(){
        return "error/runtime";
    }


}
