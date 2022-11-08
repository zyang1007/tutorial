package com.example.java_core.p01_common;


import org.junit.*;
import org.junit.jupiter.api.Test;

public class StringPoolTests {

    @Test
    public void testStringPoolSuccess(){
        String s1 = "cat";
        String s2 = "cat";
        String s3 = s1;

        Assert.assertEquals(s1, s2);
        Assert.assertEquals(s2, s3);
        System.out.println(s1 == s2 && s1 == s3);
    }

    @Test
    public void testStringPoolFail() {
        String s1 = "cat";
        String s2 = new String("cat");
        String s3 = new String("cat");
        String s4 = s2;

        // all true, because of asserting value instead of address?
        Assert.assertEquals(s1, s2);
        Assert.assertEquals(s2, s3);
        Assert.assertEquals(s1, s4);

        System.out.println(s1 == s2);  // false
        System.out.println(s2 == s3);  // false
        System.out.println(s4 == s2);  // true
    }
}
