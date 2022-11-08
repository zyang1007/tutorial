package com.example.java_core.p03_oop.interfaces;

/**
 * A class only can extend one class or one abstract class,
 * but it can implement multiple interfaces to achieve multiple inheritance.
 */
public class InterfaceTests implements Interface_A, Interface_B{

    // must override all abstract methods of the interfaces
    @Override
    public void saySomeThing() {  // method from Interface_A
        System.out.println("Implementation of saySomething()...");
    }

    @Override
    public void doSomeThing() { // method from Interface_A
        System.out.println("Done!");
    }


    @Override
    public int whatEver() {  // method from Interface_B
        return 100;
    }

    /**
     * If no duplicate method signature exist in the multiple interfaces,
     * override default methods is optional (not mandatory);
     * otherwise, we need to override that method - decide which default method we are going to use.
     */
    @Override
    public void printSomething() {
        Interface_A.super.printSomething();
        Interface_B.super.printSomething();
    }

    public static void main(String[] args) {
        InterfaceTests interfaceTests = new InterfaceTests();
        interfaceTests.printSomething();
        interfaceTests.saySomeThing();
        interfaceTests.doSomeThing();

        Interface_A.staticMethod(); // call the static method of interface A
    }


}
