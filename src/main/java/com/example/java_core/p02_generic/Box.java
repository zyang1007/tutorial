package com.example.java_core.p02_generic;

import com.example.java_core.p01_common.Employee;

// generics work only with reference types
// cannot use primitive data types when declare an instance
public class Box<T> {  // generic class

    private T obj;

    public Box() {}

    public Box(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
