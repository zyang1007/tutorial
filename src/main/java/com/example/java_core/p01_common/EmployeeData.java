package com.example.java_core.p01_common;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001, "Alice", 50, 300000.00));
        list.add(new Employee(1002, "Ben", 49, 290000.00));
        list.add(new Employee(1003, "Claire", 48, 280000.00));
        list.add(new Employee(1004, "Daniel", 47, 270000.00));
        list.add(new Employee(1005, "Emma", 46, 260000.00));
        list.add(new Employee(1006, "Felix", 45, 250000.00));

        return list;
    }
}
