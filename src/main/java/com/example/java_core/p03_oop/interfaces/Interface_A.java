package com.example.java_core.p03_oop.interfaces;

/**
 * Interfaces can have abstract, static, and default method,
 * but not Constructors, normal methods, and static block.
 */
public interface Interface_A {

    // static { System.out.println("this is Static Block of Interface A"); } // error, cannot have static block

    // public boolean isCapable() { return false; } // error, neither can have normal methods

    void saySomeThing();  // if method no modifier, abstract by default

    abstract void doSomeThing(); // abstract methods, signature only, no function body

    default void printSomething() {  // default methods can have body
        System.out.println("default method of Interface A.");
    }

    static void staticMethod() {  // static method
        System.out.println("Static Method of Interface A!");
    }
}
