package com.example.java_core.p01_common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeRepository {

    private static Set<Employee> employeeSet = new HashSet<>();

    static {
        employeeSet.addAll(EmployeeData.getEmployees());
    }

    private static void printEmployeeSet() {
        System.out.println("--- printEmployeeSet() starts --- ");

//        for (Employee e : employeeSet){  // print out in non-order
//            System.out.println(e);
//        }

        employeeSet.stream().forEach(e -> System.out.println(e));

        System.out.println("--- printEmployeeSet() end --- \n");
    }

    public static void printEmployeeList(){
        System.out.println("--- printEmployeeList() starts --- ");
        List<Employee> employeeList = EmployeeData.getEmployees();

//        for (Employee e : employeeList){
//            System.out.println(e);
//        }

        for (int i = 0; i < employeeList.size(); i++){
            Employee e = employeeList.get(i);
            System.out.println(e);
        }

        System.out.println("--- printEmployeeList() end --- \n");
    }

    public static void main(String[] args) {

        printEmployeeList();

        printEmployeeSet();
    }
}
