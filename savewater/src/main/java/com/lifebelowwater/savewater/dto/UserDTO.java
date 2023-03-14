package com.lifebelowwater.savewater.dto;

import com.lifebelowwater.savewater.entity.Address;
import com.lifebelowwater.savewater.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Collection;

@Data
public class UserDTO {


    private int userid;

    private String username;

    private String firstname;

    private String lastname;

    private int phone;

    private Address address;

    private String email;

    private String password;

    public UserDTO(int userid, String username, String firstname, String lastname, int phone, Address address, String email, String password) {
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
