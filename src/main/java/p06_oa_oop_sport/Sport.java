package p06_oa_oop_sport;

import java.util.Arrays;

public interface Sport {

    default void calculateAvgAge(int[] ages) {
        double res = Arrays.stream(ages).average().getAsDouble();
        System.out.println("Average age:  " + res);
    }

    void retirePlayer(int id);
}
