package com.example.java_core.p06_java8.lambda;

/**
 * By using the @FunctionalInterface annotation, the compiler will trigger an error in response to any attempt to
 * break the predefined structure of a functional interface. It is also a very handy tool to make our application
 * architecture easier to understand for other developers.
 */
@FunctionalInterface
public interface Bar {

    String abstractMethod(String str);  // abstract method

    default String defaultMethodBar() {
        return "defaultMethod() from Bar.";
    }

    default String defaultCommon() {
        return "defaultCommon() from Functional interface Bar.";
    }
}
