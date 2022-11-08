package com.example.java_core.p03_oop.encapsulation;

public class Encapsulate_User {

    // private fields improve accessibility/security
    private String userName;
    private String password;

    public Encapsulate_User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void login(String userName, String password) {
        if (this.userName.equals(userName) && this.password.equals(password)) {
            System.out.println("Access granted --> " + "User: " + userName);
        } else {
            System.out.println("Access denied, invalid credentials!");
        }
    }

    // add getters and setters if needed
}
