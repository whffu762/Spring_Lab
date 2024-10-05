package com.mang.example.security.EntityTest;

import com.mang.example.security.forTest.testEntity.Member;
import com.mang.example.security.forTest.testEntity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.*;

@SpringBootTest
public class EntityTest {

    @PersistenceUnit
    EntityManagerFactory emf;

    @BeforeEach
    public void init(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team("teamA");
        em.persist(team);

        Member member = new Member("kim", team);
        em.persist(member);

        tx.commit();
    }

    @Test
    public void test() {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member find = em.find(Member.class, "kim");
        Team findT = find.getTeam();
        System.out.println("findT.getClass() = " + findT.getClass());
        String name = findT.getName();

        tx.commit();
    }

    /**
        @OneToMany(mappedBy = "team", orphanRemoval = true)
        위처럼 orphanRemoval 옵션만 활용할 땐 아래와 같이 두 엔티티에서 모두 제거해 줘야 함
        근데 Spring Boot 2.3.0 의 JPA 에선 해당 옵션만 설정하면 적용되지 않는다고 함 - 버그
        그래서 반드시 cascade 옵션과 같이 써야 함 애초에 실무에서 이것만 따로 쓰는 경우가 없기도 하고 
     */
    @Test
    public void test2(){

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member find = em.find(Member.class, "kim");
        Team findT = find.getTeam();

        //Member 에서도 Team 과 연관 관계를 끊음
        find.setTeam(null);

        //Team 에서도 Member 와 연관 관계를 끊음
        findT.getMembers().remove(find);

        tx.commit();
    }
    
    /**
        @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
        위처럼 cascade와  orphanRemoval 옵션을 함께 활용할 땐 아래와 같이 부모 엔티티에서만 제거해줘도
        삭제가 됨 자세한건 노션 참고
     */
    @Test
    public void test3(){

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member find = em.find(Member.class, "kim");
        Team findT = find.getTeam();

        //Team 에서도 Member 와 연관 관계를 끊음
        findT.getMembers().remove(find);

        tx.commit();
    }
    
}
