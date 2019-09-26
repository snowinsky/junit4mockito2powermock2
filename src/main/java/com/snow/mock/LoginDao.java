package com.snow.mock;

public class LoginDao {

    public int login(UserForm user){
        System.out.println(this.getClass().getName() + ".login is running...");
        return 1;
    }
}
