package com.sunrisedental.model;

public class User {
    private int userId; 
    private String username; 
    private String password; 
    private String fullName; 
    private String role;
    public int getUserId(){return userId;} 
    public void setUserId(int v){userId=v;}
    public String getUsername(){return username;} 
    public void setUsername(String v){username=v;}
    public String getPassword(){return password;} 
    public void setPassword(String v){password=v;}
    public String getFullName(){return fullName;} 
    public void setFullName(String v){fullName=v;}
    public String getRole(){return role;} 
    public void setRole(String v){role=v;}
}
