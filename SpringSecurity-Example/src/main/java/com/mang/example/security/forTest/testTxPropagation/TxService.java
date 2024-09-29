package com.mang.example.security.forTest.testTxPropagation;

import com.mang.example.security.enums.role.UserRole;
import com.mang.example.security.forTest.testAOP.AOPUserRepository;
import com.mang.example.security.forTest.testAOP.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TxService {

    private final TxPropagation txPropagation;

    private final AOPUserRepository repository;

    @Transactional
    public void test(UserDTO userDTO) throws SQLException {

        log.info("txService2 : {}", TransactionSynchronizationManager.getCurrentTransactionName());
        repository.save(new UserDTO("service1", UserRole.USER));
        txPropagation.outerTx(userDTO);
    }
}
