package com.mang.example.security.TxPropagationTest;

import com.mang.example.security.enums.role.UserRole;
import com.mang.example.security.forTest.testAOP.AOPUserRepository;
import com.mang.example.security.forTest.testAOP.AOPUserService;
import com.mang.example.security.forTest.testAOP.UserDTO;
import com.mang.example.security.forTest.testTxPropagation.TxPropagation;
import com.mang.example.security.forTest.testTxPropagation.TxService;
import com.mang.example.security.forTest.testTxPropagation.TxService2;
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

    @Autowired
    TxService2 txService2;

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

        @Bean
        TxService txServiceTest() { return new TxService(txPropagation(), repository()); }

        @Bean
        TxService2 txService2Test() { return new TxService2(txServiceTest(), repository()); }
    }

    @Test
    public void test() throws SQLException {

        txPropagation.outerTx(new UserDTO("Exception", UserRole.USER));

    }

    @Test
    public void test2() throws SQLException{

        txService2.test(new UserDTO("kim", UserRole.USER));
    }
}
