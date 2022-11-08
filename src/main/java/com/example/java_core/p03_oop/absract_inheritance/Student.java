package com.example.java_core.p03_oop.absract_inheritance;

public class Student extends Person{

    static {
        System.out.println("this is static block of Student class!");
    }

    // constructors
    public Student() {
        System.out.println("constructor of student");
    }

    public Student(String name, String phoneNumber) {
        // System.out.println("constructor of student"); // error. call to the super must be the first statement

        super(name, phoneNumber);
        System.out.println("constructor of student");
    }

    // child class must implement the abstract methods of parent class
    @Override
    public boolean isSleeping() {
        return false;
    }

    // override speak() for differ functionality
    @Override
    public void speak() {
        super.speak();
    }

    public void speak(String str) {
        System.out.println("Student '" + this.getName() + "' is speaking..." );
    }

    public static void main(String[] args) {
        // Student s = new Student();
        Student s1 = new Student("who", "123");
        System.out.println("Is sleeping = " + s1.isSleeping());
        s1.speak();
        s1.speak("");

        /**
         * execute order:
         * 1. parent class static block
         * 2. current class static block
         * 3. parent constructor
         * 4. current constructor
         * 5. other called methods
         */

        Student.staticMethod();
        Person.staticMethod();
    }

}
