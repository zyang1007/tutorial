package com.example.java_core.p03_oop.encapsulation;

public class Encapsulate_User_Tests {

    public static void main(String[] args) {
        Encapsulate_User encapsulate_user = new Encapsulate_User("admin", "admin");

        encapsulate_user.login("admin", "admin");
        encapsulate_user.login("admin", "123");
    }
}
