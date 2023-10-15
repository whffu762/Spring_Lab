package com.mang.example.security.forTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Slf4j
public class testClass {

    /**
        @Configuration 을 테스트 하기 위한 클래스

        요약하면 @Component 를 이용해도 동작은 제대로 하지만 실제로 싱글톤이 보장되지 않음
        Spring Security 에선 @EnableWebSecurity 안에 @Configuration 이 포함되어 있음
        자세한 내용은 Bean 등록 문서 참고
     */
    private final PasswordEncoder passwordEncoder;

    public void test(){
        log.info("testClass pwEncoder = {}", passwordEncoder);
    }

}
