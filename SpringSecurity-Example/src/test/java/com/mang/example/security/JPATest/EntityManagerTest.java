package com.mang.example.security.JPATest;

import com.mang.example.security.forTest.testJPA.ServiceB;
import com.mang.example.security.forTest.testJPA.ServiceC;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 이 테스트가 안 될 경우 txPropagation 을 Bean 으로 등록했는지 확인해야 함
 * 테스트 때문에 DataSource 를 직접 넣어야 했는데 여기선 필요 없으므로 자동 등록으로 바꿔도 됨
 */
@SpringBootTest
@Slf4j
public class EntityManagerTest {

    @Autowired
    ServiceC serviceC;

    @Autowired
    ServiceB serviceB;

    @Test
    public void test(){

        //같은 트랜잭션에 묶여있으므로 같은 EntityManager 를 사용함
        serviceC.testC("kim", 10);

        log.info("========== no @Tx ============");
        //@Transactional 어노테이션이 쓰이지 않아서 다른 트랜잭션임 그럴 경우 EntityManager 도 다름
        serviceC.testC2("lee", 20);
    }

    @Test
    public void test2(){
        /*
            setter 말고 생성자로도 가능함
         */
        serviceB.testB("kim", 10);
    }
}
