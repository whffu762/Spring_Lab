package com.mang.example.security.forTest.testAOP;

import com.mang.example.security.enums.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {

    private String name;

    private UserRole role;
}
