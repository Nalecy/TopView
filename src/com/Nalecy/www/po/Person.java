package com.Nalecy.www.po;

public abstract class Person{
     private Integer id;
     private String userName;
     private String password;
     private String telephone;
    private String idNumber;
    Integer permission;



    public Person() {
        userName = "";
        password = "";
        telephone = "";
        idNumber = "";
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public Integer getPermission() { return permission; }
}
