package com.mang.example.security.forTest.testEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    public Team (String name){
        this.name = name;
    }

}
