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
public class ServiceB {

    private final EntityManager entityManager;

    @Transactional
    public void testB(String name, int age){

        log.info("session testB : {}", entityManager.unwrap(Session.class));

        log.info("em testB : {}", entityManager);

        log.info("tx testB : {}", TransactionSynchronizationManager.getCurrentTransactionName());

        UserDTO user = new UserDTO(name, age);
        //user.setName(name);
        //user.setAge(age);
        entityManager.persist(user);

    }
}
