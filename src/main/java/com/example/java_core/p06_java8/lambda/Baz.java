package com.example.java_core.p06_java8.lambda;

public interface Baz {

     default String defaultMethodBaz() {
        return "defaultMethod() from Baz!";
    }

     default String defaultCommon() {
        return "defaultCommon() from Baz!";
    }
}
