package com.expriment.Testing.study;

class Parent {
    public  void display() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
    public  void display() {
        System.out.println("Static method in Child");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent obj1 = new Parent();
        Parent obj2 = new Child();
        Child obj3 = new Child();

        obj1.display(); // Calls Parent's static method
        obj2.display(); // Calls Parent's static method (method hiding)
        obj3.display(); // Calls Child's static method
    }
}

/*
class Parent {
     static void display() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
     static void display() {
        System.out.println("Static method in Child");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent.display(); // Output: Static method in Parent
        Child.display(); // Output: Static method in Child
    }
}*/

