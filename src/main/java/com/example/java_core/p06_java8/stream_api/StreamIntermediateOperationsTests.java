package com.example.java_core.p06_java8.stream_api;

import com.example.java_core.p01_common.Employee;
import com.example.java_core.p01_common.EmployeeData;
import org.junit.jupiter.api.*;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.*;

/**
 * Intermediate operations
 * 1. select/filter: distinct(), filter(), limit(), skip();
 * 2. mapping: map(), flatmap();
 * 3. others: sorted()
 */
public class StreamIntermediateOperationsTests {

    List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);


    @Test
    public void testDistinctMethod() {
        int[] arr = {1, 2, 3, 3, 4, 2, 5};
        IntStream intStream = Arrays.stream(arr);

        System.out.print("arr: ");
        Arrays.stream(arr).forEach(e -> System.out.print(e + ", "));
        System.out.println();

        System.out.print("select distinct number from arr:  ");
        intStream.distinct().forEach(e -> System.out.print(e + ", "));
    }

    @Test
    public void testFilterMethod() {
        System.out.println("integerList: " + integerList);
        System.out.print("select even numbers from the integerList:  ");
        integerList.stream().filter(e -> e % 2 == 0).forEach(e -> System.out.print(e + ", "));

    }

    @Test
    public void testLimitMethod() {
        System.out.println("integerList: " + integerList);
        System.out.print("select the first three numbers from the integerList:  ");

        integerList.stream().limit(3).forEach(e -> System.out.print(e + ", "));

    }

    @Test
    public void testSkipMethod() {
        System.out.println("integerList: " + integerList);
        System.out.print("skip the first 2 elements of the list:  ");
        integerList.stream().skip(2).forEach(e -> System.out.print(e + ", "));
    }

    @Test
    public void testMapMethod() {
        System.out.println("integerList: " + integerList);
        System.out.print("convert elements from Integer to Double:  ");
        integerList.stream().map(Double::valueOf).forEach(e -> System.out.print(e + ", "));
    }

    // testHelper() returns a stream for each input String
    private Stream<String> testHelper(String str) {
        return Stream.of(str + "x");
    }

    @Test
    public void testFlatmapMethod_1() {
        List<String> stringList = Arrays.asList("aa", "bb", "cc", "dd", "ee");

        System.out.println("--- using map() ---");
        Stream<Stream<String>> streamStream = stringList.stream().map(e -> testHelper(e));
        streamStream.forEach(System.out::println);  // prints out addresses of the sub-streams

        // flatMap() processes all the data of multiple streams and links them into on stream
        System.out.println("--- using flatmap() ---");
        Stream<String> stream = stringList.stream().flatMap(e -> testHelper(e));
        stream.forEach(System.out::println);
    }

    @Test
    public void testFlatmapMethod_2() {
        List<String> stringList1 = Arrays.asList("1", "2", "3", "4", "5");
        List<String> stringList2 = Arrays.asList("6", "7", "8", "9", "10");

        List<List<String>> listList = new ArrayList<>();
        listList.add(stringList1);
        listList.add(stringList2);
        System.out.println("----- using map() -----");
        Stream<List<Integer>> listStream = listList.stream()
                .map(list -> list.stream().map(Integer::valueOf).collect(Collectors.toList()));
        // listStream.forEach(System.out::println);
        OptionalInt max1 = listStream.map(list -> Collections.max(list)).mapToInt(Integer::intValue).max();
        System.out.println("max1:  " + max1);

        System.out.println("----- 使用flatMap -----");
        Stream<Integer> integerStream = listList.stream()
                .flatMap(nums -> nums.stream().map(Integer::parseInt).collect(Collectors.toList()).stream());
        // 2 lists is flapped to 1 list, so just call max to get the MAX
        OptionalInt max2 = integerStream.mapToInt(Integer::intValue).max();
        System.out.println("max2:  " + max2);

    }

    @Test
    public void testIntermediateOperationChain() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = list1.stream().collect(Collectors.toList());
        System.out.println("list2:  " + list2);

        list2.addAll(
                integerList.stream().filter(num -> num % 2 == 0).map(e -> e * 10).collect(Collectors.toList()));
        long count = integerList.stream().filter(e -> e % 2 ==0).count();

        System.out.print("even numbers in the integerList:  ");
        integerList.stream().filter(num -> num % 2 == 0).forEach(e -> System.out.print(e + ", "));
        System.out.println("\ncount of even number of integerList:  " + count);

        System.out.println(list2);
    }

    @Test
    public void testSortedMethod() {
        List<Integer> integerList = Arrays.asList(5, 50, 15, 35, 25, 45);
        integerList.stream().sorted().forEach(e -> System.out.print(e + ", "));
        System.out.println();

        List<Employee> employeeList = EmployeeData.getEmployees();
        employeeList.stream().sorted(Comparator.comparing(Employee::getAge)).forEach(System.out::println);
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println();
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println();
    }

}
