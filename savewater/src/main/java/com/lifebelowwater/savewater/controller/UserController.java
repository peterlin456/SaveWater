package com.lifebelowwater.savewater.controller;


import com.lifebelowwater.savewater.dto.LoginDTO;
import com.lifebelowwater.savewater.dto.UserDTO;
import com.lifebelowwater.savewater.response.LoginMessage;
import com.lifebelowwater.savewater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public String saveUser(@RequestBody UserDTO userDTO) {
        String id = userService.addUser(userDTO);
        return  id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        LoginMessage loginMessage = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }



}
