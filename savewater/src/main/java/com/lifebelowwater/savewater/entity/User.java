package com.lifebelowwater.savewater.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Collection;

@Entity
@Table(name = "user")
@Data
public class User {


    @Id
    @Column(name="user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(name="user_name", nullable=false,length = 255)
    private String username;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastname;

    @Column(name = "phone_number", nullable = false, length = 20)
    private int phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name="email",nullable=false, unique=true, length = 255)
    private String email;

    @Column(name="password", nullable=false,length = 60)
    private String password;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable()
//    private Collection<Role> role;

    public User(){

    }
    public User(int userid, String username, String firstname, String lastname, int phone, String address, String email, String password) {
        this.userid = userid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;

    }
}
