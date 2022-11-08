package com.example.java_core.p02_generic;

import com.example.java_core.p01_common.Employee;

// generics work only with reference types
// cannot use primitive data types when declare an instance
public class GenericObject<T> {

    T obj;

    public GenericObject(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        // GenericObject<int> obj0 = new GenericObject<>(10);  // error, cannot use primitive type

        GenericObject<Integer> obj1 = new GenericObject<>(10);
        System.out.println(obj1.getObj());

        GenericObject<Double> obj2 = new GenericObject<>(20.0);
        System.out.println(obj2.getObj());

        GenericObject<String> obj3 = new GenericObject<>("abc");
        System.out.println(obj3.getObj());

        GenericObject<Employee> obj4 = new GenericObject<>(new Employee(9999, "name", 30, 100000));
        System.out.println(obj4.getObj());
    }

}
