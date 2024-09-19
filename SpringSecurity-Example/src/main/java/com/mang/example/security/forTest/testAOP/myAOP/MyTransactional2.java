package com.mang.example.security.forTest.testAOP.myAOP;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
class MyTransactional2 implements MethodInterceptor {

    Object target;

    PlatformTransactionManager txManager;

    MyTransactional2(Object target, PlatformTransactionManager txManager) {
        this.target = target;
        this.txManager = txManager;
    }

    @Override
    public Object intercept( Object o, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {

        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());

        try {
            //본체 로직 호출
            Object result = methodProxy.invokeSuper(target, args);

            txManager.commit(status);
            return result;
        } catch (Exception e){
            txManager.rollback(status);
            throw e;
        }
    }
}
