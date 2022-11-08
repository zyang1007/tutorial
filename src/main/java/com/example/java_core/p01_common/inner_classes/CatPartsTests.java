package com.example.java_core.p01_common.inner_classes;

import org.junit.jupiter.api.Test;

public class CatPartsTests {

    @Test
    public void testStaticInnerClass() {
        CarParts.StaticWheel wheel = new CarParts.StaticWheel();
        wheel.drive();
    }

    @Test
    public void testNonStaticInnerClass() {
        CarParts.NonStaticWheel nonStaticWheel = new CarParts().new NonStaticWheel();
    }

    @Test
    public void testCarPartSelfMethods(){
        CarParts.combine();  // static method belongs to the class

        CarParts carParts = new CarParts();
        carParts.drive(); // non-static method belongs to the objects of the class
    }
}
