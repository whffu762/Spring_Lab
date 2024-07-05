package com.mang.example.security.injectionTest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
public class TestInjection {

    @Autowired
    Car car;

    /*
        컴포넌트 스캔을 통한 자동 등록했을 때의 주입 순서
        INFO 9644 --- [    Test worker] c.m.example.security.injectionTest.Door  : door 생성
        INFO 9644 --- [    Test worker] c.m.example.security.injectionTest.Car   : door 생성자 주입
        INFO 9644 --- [    Test worker] c.m.e.security.injectionTest.Window      : window 생성
        INFO 9644 --- [    Test worker] c.m.e.security.injectionTest.Wheel       : Wheel 생성
        INFO 9644 --- [    Test worker] c.m.example.security.injectionTest.Car   : wheel setter 주입
        
     */
    @Test
    public void test(){
        System.out.println(car.door.door);
        System.out.println(car.window.window);
        System.out.println(car.wheel.wheel);
    }
    
    /*
        수동 등록 했을 때의 주입 순서
        Bean 생성 코드가 작성된 순서에 따라 달라짐
        그래도 생성자 주입에 필요한 door 가 먼저 생성되고 주입되고 그 다음 setter 순으로 생성되는건 흐름은 동일함
        필드 주입되는 window 만 자유로움
     */
    @Test
    public void test2(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(config.class);
        Car car = ac.getBean(Car.class);

        System.out.println(car.door.door);
        System.out.println(car.window.window);
        System.out.println(car.wheel.wheel);
    }


    @Configuration
    public static class config{

        @Bean
        public Car makeCar() {

            Car car = new Car(makeDoor());
            car.setWheel(makeWheel());

            return car;
        }

        @Bean
        public Window makeWindow(){
            return new Window();
        }

        @Bean
        public Wheel makeWheel(){
            return new Wheel();
        }

        @Bean
        public Door makeDoor(){
            return new Door();
        }
    }
}
