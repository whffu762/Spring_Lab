package com.mang.example.security.forTest.testJPA;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceA {


    private final EntityManager entityManager;

    @Transactional
    public void testA(String name, int age){

        //같은 EntityManager 인지 확인하려면 프록시 객체에서 실제로 사용하는 Session 을 까봐야 함
        log.info("session testA : {}", entityManager.unwrap(Session.class));
        
        //EntityManager 를 까보면 프록시 인스턴스가 나옴
        log.info("em testA : {}", entityManager);

        //같은 트랜잭션인지 확인
        log.info("tx testB : {}", TransactionSynchronizationManager.getCurrentTransactionName());

        UserDTO user = new UserDTO();
        user.setName(name);
        user.setAge(age);
        entityManager.persist(user);
    }

}
