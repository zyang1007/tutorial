package com.example.java_core.p06_java8.lambda;

// Note: interface can extend other interfaces(not classes), but "implements" is not allowed.
public interface Foo extends Baz, Bar {

//    @Override
//    String abstractMethod(String str);  // abstract method from Functional interface Bar

    @Override
    default String defaultCommon() {
        return "defaultCommon() from Foo!";
    }

}
