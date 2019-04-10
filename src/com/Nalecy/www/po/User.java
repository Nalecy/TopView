package com.Nalecy.www.po;

public class User {
    private Integer id;
    private String userName;
    private String password;
    private boolean hasLogin;

    public User(){
        hasLogin = false;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isHasLogin() { return hasLogin; }
    public void setHasLogin(boolean hasLogin) { this.hasLogin = hasLogin; }
}
