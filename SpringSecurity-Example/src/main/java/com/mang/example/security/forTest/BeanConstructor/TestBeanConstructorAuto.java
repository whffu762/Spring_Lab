package com.mang.example.security.forTest.BeanConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 자동 Bean 등록되는 클래스로 TestBeanConstructorNotAuto 에 주입 될 대상
 */
@Component
@Slf4j
public class TestBeanConstructorAuto {

    public void test(){
        log.info("자동 등록된 빈이 수동 등록된 빈의 생성자에 자동 주입");
    }

}
