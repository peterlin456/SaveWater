package com.lifebelowwater.savewater.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {


    private int userid;

    private String username;

    private String firstname;

    private String lastname;

    private int phone;

    private String address;

    private String email;


    private String password;

    public UserDTO(int userid, String username, String firstname, String lastname, int phone, String address, String email, String password) {
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
