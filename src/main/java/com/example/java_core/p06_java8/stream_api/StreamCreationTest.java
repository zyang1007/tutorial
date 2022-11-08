package com.example.java_core.p06_java8.stream_api;

import com.example.java_core.p01_common.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;


public class StreamCreationTest {

    @Test
    public void testCreateWithCollection() {  // Stream = Collections.stream()
        List<Integer> list = Arrays.asList(1,2 ,3 ,4 ,5);
        list.forEach(System.out::println);

        Stream<Integer> stream = list.stream();
        System.out.println("Stream object: " + stream);  // ReferencePipeline$Head@43a0cee9

        Stream<Integer> parallelStream = list.parallelStream();
        System.out.println("Parallel Stream object: " + parallelStream);  // ReferencePipeline$Head@eb21112
    }

    @Test
    public void testCreateWithArray() {  // Stream = Arrays.stream(arr)
        int[] arr = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(arr);
        System.out.println("Stream object: " + intStream);  // IntPipeline$Head@2eda0940

        Employee e1 = new Employee();
        Employee e2 = new Employee();
        Employee[] employees = {e1, e2};

        Stream<Employee> stream = Arrays.stream(employees);
        System.out.println("Employee stream: " + stream);  // ReferencePipeline$Head@6eceb130
    }

    @Test
    public void testCreateWithGroupOfData() {
        Stream<Double> doubleStream = Stream.of(1.0, 2.0, 3.0, 4.0, 5.0);
        doubleStream.forEach(System.out::println);
    }

    @Test
    public void testWithIterateAndGenerateMethods() {
        // print out first five even numbers start from 0, inclusive 0
        System.out.println("--- testing wth iterate() ---");
        Stream.iterate(0, n -> n + 2).limit(5).forEach(System.out::println);
        List<Integer> evenNums = Stream.iterate(0, n -> n + 2).limit(5).collect(Collectors.toList());
        evenNums.forEach(e -> System.out.print(e + ", "));

        System.out.println("\n\n--- testing wth generate() ---");
        List<Double> randomNums = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
        randomNums.forEach(e -> System.out.println(e + ", "));

        System.out.println("\n--- generate string ---");
        Stream<String> stringStream = Stream.generate(() -> "element").limit(3);
        stringStream.forEach(e -> System.out.print(e + ", "));
    }

    @Test
    public void testStreamRangeMethod() {
        IntStream intStream = IntStream.range(1, 5);  // exclusive 5
        System.out.println("IntStream object: " + intStream);
        intStream.forEach(e -> System.out.print(e + ", "));

        LongStream longStream = LongStream.rangeClosed(1, 5);  // inclusive 5
        System.out.println("\nLongStream object: " + longStream);
        longStream.forEach(e -> System.out.print(e + ", "));
    }

}
