package com.mang.example.security.EntityTest;

import com.mang.example.security.forTest.testEntity.Member;
import com.mang.example.security.forTest.testEntity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.*;

@SpringBootTest
public class EntityTest {

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void test() {

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Team team = new Team("teamA");
        em.persist(team);

        Member member = new Member("kim", team);
        em.persist(member);

        em.flush();
        em.clear();

        Member find = em.find(Member.class, 2L);
        Team findT = find.getTeam();
        String name = findT.getName();


        tx.commit();
    }

}
