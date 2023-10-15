package com.mang.example.security.forTest.BeanConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 테스트가 이루어지는 Controller 의존성 주입이 제대로 되는가만 확인하면 되기 때문에 반환값을 따로 설정하지 않음
 Error 발생하긴 하는데 test() 의 로그가 제대로 찍히는지 확인만 하면 되기 때문에 동작 확인하는데 문제는 없음
 */

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestBeanConstructorNotAuto testBeanConstructorNotAuto;

    @GetMapping("test1")
    public void testBeanConstructor(){

        testBeanConstructorNotAuto.test();

    }

}
