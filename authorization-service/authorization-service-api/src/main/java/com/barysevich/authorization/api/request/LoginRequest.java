package com.barysevich.authorization.api.request;

import com.barysevich.project.email.Email;

public class LoginRequest {

    private Email login;

//    private PasswordSHA256 password;
//
//    private UserEnvironment environment;


    public LoginRequest(Email login) {
        this.login = login;
    }

    public Email getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "login=" + login +
                '}';
    }
}
