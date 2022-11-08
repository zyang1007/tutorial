package com.example.java_core.p01_common;

import java.util.HashSet;
import java.util.Set;

public class JvmLoad {

    static class Demo {
        private static int n1;
        private static final String s1 = "static variable";
        private String s2 = "non static variable";
        private static Set<Employee> employeeSet = new HashSet<>();

        static {
            System.out.println("> 1, static block is called...");
            System.out.println(">> 2, check values of static variables...");
            System.out.println("n1 = " + n1);
            System.out.println("s1 = " + s1);
            // non-static variable s2 cannot be referenced from a static context
            // System.out.println("s2 = " + s2);
            System.out.println("employeeSet = " + employeeSet);

            employeeSet.addAll(EmployeeData.getEmployees());
        }

        public Demo(){
            System.out.println(">>> 3, inner class constructor is called...");
            System.out.println("n1 = " + n1);
            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
            // System.out.println("employeeSet = " + employeeSet);
            employeeSet.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}
