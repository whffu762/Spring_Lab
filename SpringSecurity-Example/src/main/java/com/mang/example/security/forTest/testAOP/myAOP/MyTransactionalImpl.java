package com.mang.example.security.forTest.testAOP.myAOP;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Aspect
@RequiredArgsConstructor
public class MyTransactionalImpl {

    private final PlatformTransactionManager transactionManager;

    @Around("@annotation(MyTransactional)")
    public Object transactional(ProceedingJoinPoint joinPoint) throws Throwable {

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Exception e){
            transactionManager.rollback(status);
            throw e;
        }
    }
}
