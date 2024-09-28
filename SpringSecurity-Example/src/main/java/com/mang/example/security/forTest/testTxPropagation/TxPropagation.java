package com.mang.example.security.forTest.testTxPropagation;

import com.mang.example.security.enums.role.UserRole;
import com.mang.example.security.forTest.testAOP.AOPUserRepository;
import com.mang.example.security.forTest.testAOP.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service    //이건 DataSource 를 직접 주입해야 할 땐 제거해야 함 근데 다른 테스트할 때 이거 없으면 계속 주입해야 해서 일단은 넣어둠
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
