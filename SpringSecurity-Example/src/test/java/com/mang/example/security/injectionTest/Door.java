package com.mang.example.security.injectionTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Door {

    String door;
    
    public Door(){
        door = "문";
        log.info("door 생성");
    }
}
