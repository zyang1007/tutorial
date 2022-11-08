package com.example.java_core.p06_java8.stream_api;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExercise {
    private List<String> words = Arrays.asList("hello", "world");

    @Test
    public void testDeduplicateCharacterWithMap() {
        // map() generate multiple streams for all strings, but does not combine them
        List<Stream<String>> listStream = words.stream()
                .map(word -> Arrays.stream(word.split("")))
                .distinct()  // distinct stream?
                // .map(e -> e.distinct())  // deduplicate character but only works in each stream
                .collect(Collectors.toList());

        listStream.forEach(System.out::println);  // prints out addresses of the streams
        listStream.stream().forEach(s -> s.forEach(e -> System.out.print(e + ",  ")));

        // Maybe there is a way to combine the streams to one main stream, but too complicated than using flatMap().

        // listStream.stream().map(e -> e.distinct().collect(Collectors.toList())).collect(Collectors.toList());
        // List<String> stringList1 = listStream.stream().map(e -> e.)).collect(Collectors.toList());
    }

    @Test
    public void testDeduplicateCharacterWithFlatMap() {
        // flatMap() generates a stream for each string in the list, and split each string to characters
        // finally combine those streams to one stream, and collect into a new list
        List<String> stringList = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()  // deduplicate character
                .collect(Collectors.toList());

        stringList.forEach(e -> System.out.print(e + ",  "));
        System.out.println();
    }
}
