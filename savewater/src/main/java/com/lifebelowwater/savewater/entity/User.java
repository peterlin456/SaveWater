package com.lifebelowwater.savewater.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name="user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(name="user_name", length = 255)
    private String username;

    public User(int userid, String username, String email, String password) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Column(name="email", length = 255)
    private String email;

    @Column(name="password", length = 60)
    private String password;

}
