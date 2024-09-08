package com.mang.example.security.forTest.testAOP;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AOPUserService {

    private final AOPUserRepository repository;

    public void myLogicWithoutTx() throws SQLException {

        List<UserDTO> users = repository.findAll();

        for(UserDTO user : users){
            checkValid(user);
            repository.update(user.getName());
        }
    }

    @Transactional
    public void myLogic() throws SQLException {

        List<UserDTO> users = repository.findAll();

        for(UserDTO user : users){
            checkValid(user);
            repository.update(user.getName());
        }
    }

    public void checkValid(UserDTO user) {
        if(user.getRole() == null){
            throw new RuntimeException("NO ROLE ERROR");
        }
    }
}
