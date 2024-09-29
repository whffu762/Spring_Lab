package com.mang.example.security.forTest.testJPA;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ServiceC {

    private final ServiceA serviceA;
    private final ServiceB serviceB;

    @Transactional
    public void testC(String name, int age){

        serviceA.testA(name+1, age+1);
        serviceB.testB(name, age);
    }

    public void testC2(String name, int age){

        serviceA.testA(name+1, age+1);
        serviceB.testB(name, age);
    }
}
