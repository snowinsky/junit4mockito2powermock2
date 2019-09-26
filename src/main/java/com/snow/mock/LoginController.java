package com.snow.mock;

import lombok.Setter;

@Setter
public class LoginController {


    private LoginService loginService;

    public String login(UserForm user) {
        if (user == null) {
            return "ERROR";
        }
        boolean logined;

        try {
            logined = loginService.login(user);
        } catch (Exception e) {
            return "ERROR EXCEPTION";
        }

        if (logined) {
            loginService.setCurrentUser(user.getUsername());
            return "OK";
        } else {
            return "Fail";
        }
    }
}
