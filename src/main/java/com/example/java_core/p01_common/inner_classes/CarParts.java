package com.example.java_core.p01_common.inner_classes;

public class CarParts {

    public static class StaticWheel {  // static inner class
        public StaticWheel() {
            System.out.println("Static wheels are assembled!");
        }
        public void drive() {
            System.out.println("drive with static wheel!");
        }
    }

    public class NonStaticWheel{  // non-static inner class
        public NonStaticWheel() {
            System.out.println("NonStatic wheel are assembled!");
        }
    }

    public CarParts() {  // constructor
        System.out.println("CarParts instance created!");
    }

    public static void combine(){  // static method
        System.out.println("combine car parts!");
    }

    public void drive() {
        System.out.println("drive a Car without wheels!");
    }
}
