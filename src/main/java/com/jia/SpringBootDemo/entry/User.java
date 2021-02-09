package com.jia.SpringBootDemo.entry;

import java.util.HashMap;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private List<String> hobbies;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", hobbies=" + hobbies +
                ", secrets=" + secrets +
                '}';
    }

    public User() {
    }

    private HashMap<String,String> secrets;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public HashMap<String, String> getSecrets() {
        return secrets;
    }

    public void setSecrets(HashMap<String, String> secrets) {
        this.secrets = secrets;
    }
}
