package com.example.java_core.p06_java8.lambda;

import com.example.java_core.p01_common.Employee;
import com.example.java_core.p01_common.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class LambdaExercise {


    @Test
    public void testRegularAnonymousFunction() {
        Foo foo = new Foo() {
            @Override
            public String abstractMethod(String str) { // regular anonymous function
                return str + " from Foo!";
            }
        };

        String sayHello = foo.abstractMethod("hello");
        System.out.println(sayHello);

        System.out.println(foo.defaultCommon());
        System.out.println(foo.defaultMethodBar());
        System.out.println(foo.defaultMethodBaz());
    }

    @Test
    public void testAnonymousFunctionWithLambda_1() {
        // interface Foo has only one abstract method for compiler to discover
        Foo foo = parameter -> parameter + " from Foo!";
        System.out.println(foo.abstractMethod("hi"));
        System.out.println(foo.abstractMethod("hello"));

        Foo foo1 = parameter -> {
            return parameter + " " + "from Foo!";
        };
        System.out.println(foo1.abstractMethod("hey"));
        System.out.println(foo1.abstractMethod("hi"));
    }

    @Test
    public void testAnonymousFunctionWithLambda_2() {
        Foo foo = parameter -> parameter.toUpperCase() + " from Foo";

        String str = "hello";
        String sayHello = foo.abstractMethod(str);
        System.out.println(sayHello);
    }

    @Test
    public void test_Lambda_With_Non_Effective_FinalVariable() {
        String str = "hello";
        str = "hi";  // assign a new value to the variable

        // Variable used in lambda expression should be final or effectively final
//        Foo foo = parameter -> parameter + str;  // compile warning
//
//        Foo foo = parameter -> {
//          return parameter + " " + str;  // compile warning
//        };
    }

    @Test
    public void testLambdaWithObjects() {
        List<Employee> employeeList = EmployeeData.getEmployees();

        Employee employee = employeeList.get(0);
        employee.setName("Apple");  // assign a new value to Objects' attribute does not change its address

        Foo foo = parameter -> parameter + employee.getName();
        System.out.println(foo.abstractMethod("hello "));

        foo = parameter -> {
          return parameter + " " + employee;
        };
        System.out.println(foo.abstractMethod("hello"));

        // employee = employeeList.get(1);  // error, re-assign a value to a object
    }

    /**
     * Can not directly call the overloading method of an interface with lambda expression.
     * Solutions: 1.use diff signature (no overloading), 2. cast the argument.
     *
     * @throws Exception handle the exception from the process() method.
     */
    @Test
    public void testOverloadMethods() throws Exception {
        Processor processor = new ProcessorImpl(); // overloading methods from interface Processor

        String res = processor.process((Callable<String>) () -> "abc");  // cast the argument
        System.out.println(res);
    }

}
