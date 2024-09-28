package com.mang.example.security.forTest.testTxPropagation;

import com.mang.example.security.enums.role.UserRole;
import com.mang.example.security.forTest.testAOP.AOPUserRepository;
import com.mang.example.security.forTest.testAOP.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class TxService {

    private final TxPropagation txPropagation;

    private final AOPUserRepository repository;

    //@Transactional
    public void test(UserDTO userDTO) throws SQLException {

        repository.save(new UserDTO("service1", UserRole.USER));
        txPropagation.outerTx(userDTO);
    }
}
