package com.mang.example.security.forTest.testAOP.myAOP;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MyTransactional {
}
