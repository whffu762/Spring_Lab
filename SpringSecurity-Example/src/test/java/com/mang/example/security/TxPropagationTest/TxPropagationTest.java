package com.mang.example.security.TxPropagationTest;

import com.mang.example.security.enums.role.UserRole;
import com.mang.example.security.forTest.testAOP.AOPUserRepository;
import com.mang.example.security.forTest.testAOP.AOPUserService;
import com.mang.example.security.forTest.testAOP.UserDTO;
import com.mang.example.security.forTest.testTxPropagation.TxPropagation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.SQLException;

import static com.mang.example.security.forTest.testAOP.ConnectionConst.*;

@SpringBootTest
public class TxPropagationTest {

    @Autowired
    TxPropagation txPropagation;

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
        TxPropagation txPropagation() { return new TxPropagation(repository()); }

    }

    @Test
    public void test() throws SQLException {

        txPropagation.outerTx(new UserDTO("Exception", UserRole.USER));

    }


}
