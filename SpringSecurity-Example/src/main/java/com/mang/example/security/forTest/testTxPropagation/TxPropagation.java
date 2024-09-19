package com.mang.example.security.forTest.testTxPropagation;

import com.mang.example.security.enums.role.UserRole;
import com.mang.example.security.forTest.testAOP.AOPUserRepository;
import com.mang.example.security.forTest.testAOP.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@RequiredArgsConstructor
public class TxPropagation {


    private final AOPUserRepository repository;

    @Transactional
    public void outerTx(UserDTO userDTO) throws SQLException {

        repository.save(new UserDTO("outerTx", UserRole.USER));
        innerTx(userDTO);
    }

    @Transactional
    public void innerTx(UserDTO userDTO) throws SQLException {

        if(userDTO.getName().equals("Exception")){
            throw new RuntimeException("rollback");
        }

        repository.save(userDTO);
    }
}
