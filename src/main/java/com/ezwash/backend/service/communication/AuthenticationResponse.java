package com.ezwash.backend.service.communication;

import com.ezwash.backend.resource.accounts.UserDTO;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private UserDTO user;
    private String token;

    public AuthenticationResponse(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
