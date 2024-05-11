//package com.mang.example.security.forTest.testRedis;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//
//@RestController
//public class testRedisController {
//
//    @PostMapping("/testRedis")
//    public String testRedis(HttpSession httpSession){
//
//        String id = "test1";
//        httpSession.setAttribute("SESSION_ID", id);
//
//        return "sessionTest";
//    }
//
//    @GetMapping("/getRedis")
//    public String testGet(HttpSession httpSession){
//
//        return httpSession.getId();
//    }
//
//}
