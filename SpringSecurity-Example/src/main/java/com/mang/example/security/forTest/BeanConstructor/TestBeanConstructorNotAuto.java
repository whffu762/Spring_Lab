package com.mang.example.security.forTest.BeanConstructor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestBeanConstructorNotAuto {


    /**
     config 파일에서 수동 등록 될 클래스로 생성자 인자로 TestConstructorAuto 를 받아야 함
     자동 의존성 주입을 하지 않기 위해 빈 생성자 만들어줌(생성자 두 개 이상이면 @Autowired 붙어야 자동 의존성 주입이 적용됨)
     */

    TestBeanConstructorAuto testBeanConstructorAuto;

    public TestBeanConstructorNotAuto(TestBeanConstructorAuto testBeanConstructorAuto){
        this.testBeanConstructorAuto = testBeanConstructorAuto;
    }

    public void test(){
        testBeanConstructorAuto.test();
    }
}
