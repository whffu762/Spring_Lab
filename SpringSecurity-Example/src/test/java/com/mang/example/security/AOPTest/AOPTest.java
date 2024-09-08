package com.mang.example.security.AOPTest;


import com.mang.example.security.enums.role.UserRole;
import com.mang.example.security.forTest.testAOP.AOPUserRepository;
import com.mang.example.security.forTest.testAOP.AOPUserService;
import com.mang.example.security.forTest.testAOP.ConnectionConst;
import com.mang.example.security.forTest.testAOP.UserDTO;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static com.mang.example.security.forTest.testAOP.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class AOPTest {

    @Autowired
    private AOPUserRepository repository;

    @Autowired
    private AOPUserService service;


    @TestConfiguration
    static class testConfig{

        @Bean
        DataSource dataSource(){
            return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        }

        @Bean
        AOPUserRepository repository() {
            return new AOPUserRepository(dataSource());
        }

        @Bean
        AOPUserService service() {
            return new AOPUserService(repository());
        }

    }

    @Test
    void delete() throws SQLException{
        repository.delete();
    }


    void correctData() throws SQLException {

        for(int i=0;i<4;i++){
            repository.save(UserDTO.builder()
                    .name("user" + i)
                    .role(UserRole.USER)
                    .build());
        }

        for(int i=4;i<9;i++){
            repository.save(UserDTO.builder()
                    .name("user" + i)
                    .role(UserRole.USER)
                    .build());
        }
    }
    
    
    void wrongData() throws SQLException {

        for(int i=0;i<4;i++){
            repository.save(UserDTO.builder()
                    .name("user" + i)
                    .role(UserRole.USER)
                    .build());
        }

        repository.save(UserDTO.builder()
                .name("userB")
                .role(null)
                .build());

        for(int i=4;i<9;i++){
            repository.save(UserDTO.builder()
                    .name("user" + i)
                    .role(UserRole.USER)
                    .build());
        }
    }

    @DisplayName("정상 동작")
    @Test
    public void testAOP0() throws SQLException{

        //correctData();
        service.myLogic();
    }


    @DisplayName("트랜잭션 + 비정상 동작")
    @Test
    public void testAOP() {

        //wrongData();

        assertThatThrownBy(() -> {
            service.myLogic();
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("NO ROLE ERROR");
    }


    @DisplayName("트랜잭션 x + 비정상 동작")
    @Test
    public void testAOP2() {
        
        //wrongData();
        
        assertThatThrownBy(() -> {
            service.myLogicWithoutTx();
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("NO ROLE ERROR");
    }

    @Test
    void aopCheck(){

        log.info("service = {}", service.getClass());
        log.info("repository = {}", repository.getClass());
    }
}
