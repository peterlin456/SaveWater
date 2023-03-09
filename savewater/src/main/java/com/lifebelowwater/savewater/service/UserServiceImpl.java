package com.lifebelowwater.savewater.service;

import com.lifebelowwater.savewater.dto.LoginDTO;
import com.lifebelowwater.savewater.dto.UserDTO;
import com.lifebelowwater.savewater.entity.User;
import com.lifebelowwater.savewater.repository.UserRepository;
import com.lifebelowwater.savewater.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUserid(),
                userDTO.getUsername(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getPhone(),
                userDTO.getEmail(),
                userDTO.getAddress(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepository.save(user);

        return user.getUsername();
    }

    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {

                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }
}
