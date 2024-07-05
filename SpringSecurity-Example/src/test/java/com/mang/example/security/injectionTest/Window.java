package com.mang.example.security.injectionTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Window {

    String window;
    public Window(){
        window = "창문";
        log.info("window 생성");
    }
}
