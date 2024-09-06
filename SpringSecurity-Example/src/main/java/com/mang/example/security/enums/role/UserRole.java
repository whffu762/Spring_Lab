package com.mang.example.security.enums.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    public static UserRole exchange(String value){

        for(UserRole role : UserRole.values()){
            if(role.getValue().equals(value)){
                return role;
            }
        }

        return null;
    }
}