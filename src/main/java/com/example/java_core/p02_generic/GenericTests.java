package com.example.java_core.p02_generic;

import com.example.java_core.p01_common.Employee;
import com.example.java_core.p01_common.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class GenericTests {

    @Test
    public void testGenericPrintArray() {
        Integer[] intArr = {1, 2, 3, 4, 5};
        Double[] doubleArr = {1.0, 2.0, 3.0, 4.0};
        Character[] charArr = {'a', 'b', 'c', 'd'};

        GenericMethods.printArray(intArr);
        GenericMethods.printArray(doubleArr);
        GenericMethods.printArray(charArr);

        // only reference types are allowed for type parameters, not primitive type(int, double, char..)
        int[] arr = {1, 2, 3};
        // GenericMethods.printArray(arr);  // error
    }

    @Test
    public void testGenericPrintObj() {
        int num1 = 10;
        double num2 = 20.0;
        long num3 = 30;
        GenericMethods.printObj(num1);  // method can be used because of autoboxing
        GenericMethods.printObj(num2);
        GenericMethods.printObj(num3);

        Integer num4 = 40;
        Double num5 = 50.0;
        GenericMethods.printObj(num4);
        GenericMethods.printObj(num5);
    }

    @Test
    public void testCompareTwoObj() {
        Integer integer1 = 10;
        Integer integer2 = 11;
        Object max = GenericMethods.compareTwoObj(integer1, integer2);
        System.out.println(max);

        System.out.println("Max(Double) of 5.1 and 5.0 is: " + GenericMethods.compareTwoObj(5.1, 5.0));
        System.out.println("Max(String) of qwe and asd is: " + GenericMethods.compareTwoObj("qwe", "asd"));

        // class Employee implements Comparable<Employee>, so we can use it
        Employee employee1 = new Employee(001, "a", 10, 100);
        Employee employee2 = new Employee(002, "b", 10, 50);
        System.out.println(GenericMethods.compareTwoObj(employee1, employee2));
    }

    @Test
    public void testMaxElement() {
        List<Double> list2 = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        System.out.println("Max element<Double> val: " + GenericMethods.maxElement(list2));

        List<String> list3 = Arrays.asList("ab", "abc", "abcd");
        System.out.println("Max element<String> val: " + GenericMethods.maxElement(list3));

        List<Employee> employeeList = EmployeeData.getEmployees();
        // employeeList.forEach(System.out::println);
        System.out.println("Max employee: " + GenericMethods.maxElement(employeeList));
    }

    @Test
    public void testCreateGenericObject() {
        // GenericObject<int> obj = new GenericObject<>(10);  // error, cannot use primitive type

        Box<Integer> integerBox = new Box<>(10);
        System.out.println("integerBox:  " + integerBox);  // reference address
        System.out.println("integerBox.getObj():  " + integerBox.getObj());

        Box<Double> doubleBox = new Box<>(20.0);
        System.out.println(doubleBox.getObj());

        Box<String> stringBox = new Box<>("abc");
        System.out.println(stringBox.getObj());

        Box<Employee> employeeBox = new Box<>(new Employee(9999, "name", 30, 100000));
        System.out.println("employeeBox:  " + employeeBox.getObj());
    }
}
