package com.mang.example.security.forTest.testErrorPage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//테스트를 위해 Error 를 유발하는 요청으로 이루어진 컨트롤러
@Controller
@Slf4j
public class MakingError {

    @GetMapping("/404Request")
    public void error404(HttpServletResponse response) throws IOException {
        log.info("404 에러 유도");
        response.sendError(404, "404 에러 유도");
    }

    @GetMapping("/500Request")
    public void error500(HttpServletResponse response) throws IOException {
        log.info("500 에러 유도");
        response.sendError(500, "500 에러 유도");
    }

    /**
       BasicErrorController 를 사용하여 예외를 처리하는 경우 Exception 을 구분하지 못 함
        그 이유는 WAS 까지 도달한 Exception 에 대해서 전부 500 상태코드로 취급하기 때문
     */
    @GetMapping("/RuntimeRequest")
    public void errorRuntime() {
        log.info("Runtime 에러 유도");
        throw new RuntimeException();
    }

    @GetMapping("/IllegalRequest")
    public void errorIllegal() {
        log.info("IllegalArgumentException 에러 유도");
        throw new IllegalArgumentException();
    }
}
