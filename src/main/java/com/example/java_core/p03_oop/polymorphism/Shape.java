package com.example.java_core.p03_oop.polymorphism;

public class Shape {

    public double getArea() {
        return 0;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Polymorphism: many forms  --> 1. overload; 2. override.
    // override the method of parent class with same method signature, maybe differ functionality.
    // Method Signature consists of the method name and the parameter list.
    @Override
    public double getArea() {
        return this.width * this.height;
    }
    // add getters and setters if needed
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return radius * radius * 3.14;
    }

    // method overloading: same method name and different parameter(s).
    public double getArea(boolean isExact) { // overloading method
        if (isExact) {
            return radius * radius * Math.PI;
        } else {
            return getArea();
        }
    }
}
