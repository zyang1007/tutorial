package com.example.java_core.p03_oop.absract_inheritance;

/**
 * Just like normal classes, Abstract Classes can have Constructors, abstract methods,
 * normal methods, and static block; but not default method (interface only);
 */
public abstract class Person {

    static {  // abstract classes can have static block
        System.out.println("this is Static Block of abstract class Person!");
    }

    private String name;
    private String phoneNumber;

    // abstract classes can have constructor
    public Person() {
        System.out.println("constructor of Person...");
    }

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        System.out.println("constructor of Person...");
    }

    public abstract boolean isSleeping();  // abstract methods -> signature only, no body

    // abstract class also can have normal method
    public void speak() {
        System.out.println("Person '" + this.name + "' is speaking ...");
    }


    static void staticMethod() { // a static method
        System.out.println("Static Method from Person class!");
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
