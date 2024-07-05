package com.mang.example.security.injectionTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Car {

    Door door;
    
    Wheel wheel;

    @Autowired
    Window window;

    
    public Car (Door door){
        log.info("door 생성자 주입");
        this.door = door;
    }

    @Autowired
    public void setWheel(Wheel wheel) {
        log.info("wheel setter 주입");
        this.wheel = wheel;
    }
}
