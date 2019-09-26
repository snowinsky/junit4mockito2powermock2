package com.snow.mock;

import lombok.Data;

@Data
public class LoginControllerPowerMock {

    private LoginUtil loginUtil;

    public String getConstructLogin(String name, String password) {
        return new LoginUtil(name, password).toString();
    }

    public String getStaticLogin() {
        return LoginUtil.getStaticUserName();
    }

    public String getFinalLogin() {
        return loginUtil.getCurrentUserName();
    }

    public String getPrivateLogin(){
        return loginUtil.getDefaultLogin();
    }



}
