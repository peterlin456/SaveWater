package com.lifebelowwater.savewater.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Role(String role_user) {
        this.name = role_user;
    }

}
