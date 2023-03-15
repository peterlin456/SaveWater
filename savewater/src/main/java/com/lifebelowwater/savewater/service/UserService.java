package com.lifebelowwater.savewater.service;

import com.lifebelowwater.savewater.dto.LoginDTO;
import com.lifebelowwater.savewater.dto.UserDTO;
import com.lifebelowwater.savewater.response.LoginMessage;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
     String addUser(UserDTO userDTO);

     LoginMessage loginUser(LoginDTO loginDTO);


}
