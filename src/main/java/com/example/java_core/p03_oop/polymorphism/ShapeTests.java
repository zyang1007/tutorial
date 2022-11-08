package com.example.java_core.p03_oop.polymorphism;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeTests {

    public static List<Object> getAreaTest(List<?> list) {
        List<Object> resList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            if (list.get(i) instanceof Shape) {
                double res =  ((Shape) list.get(i)).getArea();
                resList.add(res);
            }
        }

        return resList;
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Rectangle(3, 4);
        shapes[1] = new Circle(2);

        List<Shape> list = Arrays.asList(shapes);
        List<Object> resList = getAreaTest(list);

        System.out.println( resList.toString() );

        double rectangleArea = 3 * 4;
        Assert.assertEquals(rectangleArea, resList.get(0));

        double circleArea = 2 * 2 * 3.14;
        Assert.assertEquals(circleArea, resList.get(1));

    }
}
