package com.pahana.adminstaffsystem.model;


public abstract class User {
    protected String username;
    protected String password;
    
    public User(){}
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
