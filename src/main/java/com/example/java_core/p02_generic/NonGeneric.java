package com.example.java_core.p02_generic;

public class NonGeneric {

    static class MaximumTest{

        public static int maximum(int x, int y) {
            int max = x >= y ? x : y;
            return max;
        }

        public static double maximum(double x, double y) {
            double max = x >= y ? x : y;
            return max;
        }

        public static String maximum(String x, String y) {
            String max = x.compareTo(y) >= 0 ? x : y;
            return max;
        }
    }

    public static void main(String[] args) {
        System.out.println("Maximum(int) of 3 and 4 is: " + MaximumTest.maximum(3, 4));
        System.out.println("Maximum(double) of 3.0 and 3.1 is: " + MaximumTest.maximum(3.0, 3.1));
        System.out.println("Maximum(String) of abc and abb is: " + MaximumTest.maximum("abc", "abb") );
    }
}
