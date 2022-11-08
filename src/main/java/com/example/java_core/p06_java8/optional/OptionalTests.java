package com.example.java_core.p06_java8.optional;

import com.example.java_core.p01_common.Employee;
import org.junit.Test;

import java.util.Optional;

public class OptionalTests {

    @Test
    public void testOptionalWithNullValue_1() {
        Employee employee = new Employee();  // new am instance, not null
        Optional<Employee> optional = Optional.of(employee);

        employee = null;  // assign to null
        try {
            Optional<Employee> optional1 = Optional.of(employee);  // cannot pass null to Optional.of() method
            System.out.println("Done, no error detected!");

        } catch (Exception e) {
            System.out.println("Exception detected: " + e);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testOptionalWithNullValue_2() {
        Employee employee = new Employee();  // new am instance, not null
        Optional<Employee> optional = Optional.of(employee);

        employee = null;  // assign to null
        Optional<Employee> optional1 = Optional.of(employee);  // cannot pass null to Optional.of() method
    }

    @Test
    public void testOfNullable() {
        Employee employee = new Employee();
        Optional<Employee> optional = Optional.ofNullable(employee);
        System.out.println("optional 1: ");
        System.out.println(optional);  // Optional[Employee{ id=0, name='null', age=0, salary=0.0 }]
        System.out.println(optional.isEmpty());  // false
        System.out.println(optional.isPresent());  // true
        System.out.println(optional.get());  // Employee{ id=0, name='null', age=0, salary=0.0 }

        employee = null; // assign null to obj
        Optional<Employee> optional1 = Optional.ofNullable(employee);
        System.out.println("\noptional 2: ");
        System.out.println(optional1);  // Optional.empty
        System.out.println(optional1.isEmpty());  // true
        System.out.println(optional1.isPresent());  // false
        // System.out.println(optional1.get());  // NoSuchElementException

        // solution: use orElse() method
        Employee employee2 = Optional.ofNullable(employee)
                .orElse( new Employee(0001, "who", 30, 666));
        System.out.println("\nemployee: ");
        System.out.println(employee2);
    }
}
