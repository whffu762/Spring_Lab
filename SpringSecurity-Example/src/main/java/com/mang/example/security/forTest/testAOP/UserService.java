package com.mang.example.security.forTest.testAOP;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final AOPUserRepository repository;

    public void myLogicWithoutTx() throws SQLException {

        List<UserDTO> users = repository.findAll();

        for(UserDTO user : users){
            checkValid(user);
            repository.update(user.getUserEmail());
        }
    }

    @Transactional
    public void myLogic() throws SQLException {

        List<UserDTO> users = repository.findAll();

        for(UserDTO user : users){
            checkValid(user);
            repository.update(user.getUserEmail());
        }
    }

    public void checkValid(UserDTO user) {
        if(user.getRole() == null){
            throw new RuntimeException("NO ROLE ERROR");
        }
    }
}
