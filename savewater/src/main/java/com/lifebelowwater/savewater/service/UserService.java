package com.lifebelowwater.savewater.service;

import com.lifebelowwater.savewater.dto.LoginDTO;
import com.lifebelowwater.savewater.dto.UserDTO;
import com.lifebelowwater.savewater.response.LoginMessage;

public interface UserService {
     String addUser(UserDTO userDTO);

     LoginMessage loginUser(LoginDTO loginDTO);
}
