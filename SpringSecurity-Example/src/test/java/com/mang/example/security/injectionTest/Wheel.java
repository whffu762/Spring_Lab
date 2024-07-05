package com.mang.example.security.injectionTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Wheel {

    String wheel;
    public Wheel(){
        wheel = "바퀴";
        log.info("Wheel 생성");
    }
}
