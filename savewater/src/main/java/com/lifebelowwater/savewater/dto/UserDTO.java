package com.lifebelowwater.savewater.dto;

import com.lifebelowwater.savewater.entity.Address;
import com.lifebelowwater.savewater.entity.Role;

import lombok.Data;

import java.util.Collection;

@Data
public class UserDTO {


    private Long userid;

    private String username;

    private String firstname;

    private String lastname;

    private int phone;

    private Address address;

    private String email;

    private String password;


}
