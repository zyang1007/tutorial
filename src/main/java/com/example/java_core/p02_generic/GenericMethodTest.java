package com.example.java_core.p02_generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericMethodTest {

    public static void main(String[] args) {
        GenericObject<Integer> obj1 = new GenericObject<>(5);
        GenericObject<Integer> obj2 = new GenericObject<>(6);

        System.out.println("\n--- testing normal generic method ---");
        System.out.println("Max(Integer) of 5 and 6 is: " + MaxGenericObj.maximum(5, 6));
        System.out.println("Max(Double) of 5.1 and 5.0 is: " + MaxGenericObj.maximum(5.1, 5.0));
        System.out.println("Max(String) of qwe and asd is: " + MaxGenericObj.maximum("qwe", "asd"));

        System.out.println("\n--- testing collection with wildcard ---");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Max element<Integer> val: " + MaxGenericObj.maxElement(list1));

        List<Double> list2 = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        System.out.println("Max element<Double> val: " + MaxGenericObj.maxElement(list2));

        List<String> list3 = Arrays.asList("ab", "abc", "abcd");
        System.out.println("Max element<String> val: " + MaxGenericObj.maxElement(list3));
    }
}

class MaxGenericObj {  // class with generic methods
    // generic method format:  <T> returnType methodName(e.g. parameters)
    public static <T extends Comparable<T>> T maximum(T x, T y) {
        T max = x.compareTo(y) >= 0 ? x : y;
        return max;
    }

    // use wildcard for collection
    public static <T extends Comparable<T>> T maxElement(List<?> list) {
        T max = (T) list.get(0);

        for (int i = 0; i < list.size(); i++){
            if (max.compareTo((T) list.get(i)) < 0) {
                max = (T) list.get(i);
            }
        }

        return max;
    }
}
