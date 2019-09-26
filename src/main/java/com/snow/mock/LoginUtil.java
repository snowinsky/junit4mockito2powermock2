package com.snow.mock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUtil {

    private String userName;
    private String password;

    private String getDefaultLoginUserName(String aa) {
        System.out.println(this.getClass().getSimpleName() + ".getDefaultLoginUserName");
        return "root" + aa;
    }

    public final String getCurrentUserName() {
        System.out.println(this.getClass().getSimpleName() + ".getCurrentUserName");
        return "current user";
    }

    public static String getStaticUserName() {
        System.out.println(LoginUtil.class.getSimpleName() + ".getStaticUserName");
        return "static user";
    }

    public String getDefaultLogin(){
        return getDefaultLoginUserName("aaa");
    }
}
