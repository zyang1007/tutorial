package com.example.java_core.p02_generic;

import java.util.Arrays;
import java.util.List;

public class GenericMethods {

    // generic method signature format:  <T> returnType methodName(parameters)

    public static <E> void printObj(E obj) {
        System.out.println(obj);
    }

    public static <E> void printArray(E[] arr) {
        Arrays.stream(arr).forEach(e -> System.out.print(e + ",  "));
        System.out.println();
    }

    /**
     * Bounded Type Parameters
     * <T extends ...>: restrict the kinds of types that are allowed to be passed to a type parameter.
     *
     * e.g.
     * e.g. <T extends Number>; <T extends Comparable<T>>.
     */
    public static <T extends Comparable<T>> T compareTwoObj(T x, T y) {
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
