package com.example.java_core.p03_oop.interfaces;

public interface Interface_B {

    abstract int whatEver();

    default void printSomething() {
        System.out.println("default method of Interface B.");
    }
}
