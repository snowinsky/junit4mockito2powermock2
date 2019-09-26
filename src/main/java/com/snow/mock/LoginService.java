package com.snow.mock;

public class LoginService {

    private LoginDao loginDao;
    private String currentUser;

    public boolean login(UserForm user){
        assert null != user;
        System.out.println(this.getClass().getSimpleName() + ".login is running...");
        return loginDao.login(user) == 1;
    }

    public void setCurrentUser(String username){
        System.out.println(this.getClass().getSimpleName() + ".setCurrentUser is running...");
        if(null != username){
            this.currentUser = username;
        }
    }


}
