package com.example.java_core.p06_java8.stream_api;

import com.example.java_core.p01_common.Employee;
import com.example.java_core.p01_common.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. Search: findFirst(), findAny();
 * 2. Match: allMatch(), anyMatch(), nonMatch();
 * 3. reduce(BinaryOperator), reduce(T identity, BinaryOperator);  - 可以将流中的元素反复结合起来，得到一个值
 * 4. others: count(), forEach(), collect(), max(), min().
 */
public class StreamEndOperationsTest {

    private static final List<Employee> EMPLOYEE_LIST = EmployeeData.getEmployees();
    static {
        EMPLOYEE_LIST.stream().forEach(System.out::println);
        System.out.println();
//        EMPLOYEE_LIST.forEach(System.out::println);  // collection iterator
//        System.out.println();
    }

    @Test
    public void testMatchMethods() {
        // EMPLOYEE_LIST.stream().forEach(System.out::println);
        boolean allMatch = EMPLOYEE_LIST.stream().allMatch(e -> e.getAge() >= 20);
        System.out.println("check if all employee's age >= 20:  " + allMatch);

        boolean anyMatch = EMPLOYEE_LIST.stream().anyMatch(e -> e.getSalary() >= 250000);
        System.out.println("check if any employee's salary >= 250k:  " + anyMatch);

        boolean nonMatch = EMPLOYEE_LIST.stream().noneMatch(e -> e.getName().startsWith("Y"));
        System.out.println("check if no employee's name starts with Y:  " + nonMatch);
    }

    @Test
    public void testFindMethods() {
        Optional<String> name = EMPLOYEE_LIST.stream().findFirst().map(e -> e.getName());
        System.out.println("first employee's name:  " + name);

        Optional<Employee> employee = EMPLOYEE_LIST.parallelStream().findAny();
        System.out.println(employee);
    }

    @Test
    public void testReduceMethod() {
        List<Double> integerList = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        Double doubleReduce = integerList.stream().reduce(0.0, Double::sum);
        System.out.println("doubleReduce:  " + doubleReduce);

        Stream<Integer> streamOfAges1 = EMPLOYEE_LIST.stream().map(Employee::getAge);
        Optional<Integer> sumOfAges1 =  streamOfAges1.reduce(Integer::sum);
        System.out.println("sumOfAges1:  " + sumOfAges1.get());

        Stream<Integer> streamOfAges2 = EMPLOYEE_LIST.stream().map(Employee::getAge);
        Optional<Integer> sumOfAges2 = streamOfAges2.reduce((a1, a2) -> a1 + a2);
        System.out.println("sumOfAges2:  " + sumOfAges2.get());

    }

    @Test
    public void testCountMethods() {
        long count = EMPLOYEE_LIST.stream().filter(e -> e.getSalary() >= 280000).count();
        System.out.println("number of employees who's salary >= 250000:  " + count);
    }

    @Test
    public void testMaxAndMinMethod() {
        Optional<Integer> maxAge = EMPLOYEE_LIST.stream().map(Employee::getAge).max(Integer::compare);
        System.out.println("Oldest employee's age:  " + maxAge);

        Optional<Employee> youngest = EMPLOYEE_LIST.stream().min(Comparator.comparing(Employee::getAge));
        System.out.println("Youngest employee:  " + youngest);
    }

    @Test
    public void testCollectAndForEachMethod() {
        List<Employee> list = EMPLOYEE_LIST.stream().filter(e -> e.getAge() >= 48).collect(Collectors.toList());
        list.stream().forEach(System.out::println);

        Set<String>  nameSet_X = EMPLOYEE_LIST.stream().filter(e -> e.getAge() >= 48)
                .map(e -> e.getName() + " X").collect(Collectors.toSet());

        System.out.print("nameSet_X:  ");
        nameSet_X.stream().forEach(e -> System.out.print(e + ", "));

        System.out.println();
    }

    @Test
    public void testOperationChain() {
        List<Double> salaryIncremental = EMPLOYEE_LIST.stream()
                .filter(e -> e.getAge() > 47).map(e -> e.getSalary() * 1.5).collect(Collectors.toList());

        salaryIncremental.stream().forEach(e -> System.out.print(e + ",  "));
        System.out.println();

        Optional<Double> sumOfSalary = EMPLOYEE_LIST.stream()
                .filter(e -> e.getAge() < 60)
                .map(e -> e.getSalary() * 1.2)
                .map(e -> {
                    System.out.print(e + ",  ");
                    return e.doubleValue();
                })
                . reduce(Double::sum);

        System.out.println("\nsumOfSalary:  " + sumOfSalary.get());
    }
}
