package com.example.java_core.p04_enum;

import org.junit.jupiter.api.Test;


public class EnumTests {

    @Test
    public void testSeason() {
        for (Season e : Season.values()){
            System.out.println(e.name() + ", " + e.ordinal() );
        }
    }

    @Test
    public void testSeason2() {
        for (Season2 e : Season2.values()){
            System.out.println(e.ordinal() + ", " + e.name() + ", " + e.getValue());
        }
    }
}
