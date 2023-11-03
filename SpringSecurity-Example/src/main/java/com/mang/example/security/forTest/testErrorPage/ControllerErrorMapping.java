package com.mang.example.security.forTest.testErrorPage;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//예외와 에러 페이즈를 출력할 컨트롤러가 매핑
//@Component
public class ControllerErrorMapping implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {


    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/errorPage/404");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPage/500");
        ErrorPage errorEx = new ErrorPage(RuntimeException.class, "/errorPage/runtime");

        factory.addErrorPages(error404, error500, errorEx);
    }
}
