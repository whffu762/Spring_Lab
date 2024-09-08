package com.mang.example.security.forTest.testAOP;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class AOPController {

    private final AOPUserService service;

    @GetMapping("/test/aop")
    public void testController() throws SQLException {

        service.myLogic();
    }

    @GetMapping("/test/aopWithoutTx")
    void testController2() throws SQLException {
        service.myLogicWithoutTx();
    }
}
