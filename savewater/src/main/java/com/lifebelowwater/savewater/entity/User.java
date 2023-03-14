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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name="email",nullable=false, unique=true, length = 255)
    private String email;

    @Column(name="password", nullable=false,length = 60)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;
    public User() {

    }

    public User(int userid, String username, String firstname, String lastname, int phone, Address address, String email, String password, Collection<Role> roles) {
        this.userid = userid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
