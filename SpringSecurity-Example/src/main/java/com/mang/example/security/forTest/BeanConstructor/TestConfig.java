package com.mang.example.security.forTest.BeanConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 이번 테스트의 핵심
 NotAuto Bean 은 Auto Bean 을 주입받아 생성됨
 그 과정에서 Auto private final 필드를 지정해서 생성자, setter, 필드 방식으로 TestConfig 가 주입받지 않아도
 아래의 1번과 2번 코드가 없이 3번 처럼 바로 생성자에 인자로 넣어도 주입됨

 물론 1번 2번 코드를 사용한 방식도 정상적으로 동작함(주석 처리된 방식)

 */
@Configuration
//@RequiredArgsConstructor //... 1
public class TestConfig {

//    private final TestBeanConstructorAuto testConstructorAuto; // ... 2
//    @Bean
//    public TestBeanConstructorNotAuto forTest2(){
//        return new TestBeanConstructorNotAuto(testConstructorAuto);
//    }

    @Bean
    public TestBeanConstructorNotAuto forTest(TestBeanConstructorAuto testBeanConstructorAuto){ // ... 3
        return new TestBeanConstructorNotAuto(testBeanConstructorAuto);
    }
}
