package p07_oa_stream;

import java.util.Arrays;

public class Print1 {

    public static void main(String[] args) {

        System.out.println("> Stream without terminal operation:");
        Arrays.stream(new int[] {1, 2, 3}).map(e -> {
            System.out.println("doubling " + e);
            return e * 2;
        });  // .forEach(System.out::println);

        System.out.println("\n> Stream with terminal operation:");
        int sum = Arrays.stream(new int[] {1, 2, 3}).map(e -> {
            System.out.println("doubling " + e);
            return e * 2;
        }).sum();
        System.out.println("sum:  " + sum);
    }
}
