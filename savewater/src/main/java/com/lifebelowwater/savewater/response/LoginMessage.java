package com.lifebelowwater.savewater.response;

import lombok.Data;

@Data
public class LoginMessage {

    public LoginMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    String message;
    Boolean status;

}
