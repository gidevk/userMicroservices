package com.expriment.Testing.study;

@FunctionalInterface
interface FuncInter1 {
    int operation(int t, int q,int r);
}
public class lambdaFunction {
    public static void main(String[] args) {
        String p1="hello",p2="Indradev";
        FuncInter1 fn=(t,q,r)-> t/q+r;
        FuncInter1 fn1=(t,q,r)-> t%q+r;
//        (p12, p21) -> System.out.println("Multiple parameters: " + p12 + ", " + p21);

        System.out.println( fn.operation(34,13,20));
        System.out.println( fn1.operation(34,13,66));


    }
}

// Java program to demonstrate How Diamond Problem
// Is Handled in case of Default Methods

// Interface 1
interface API extends Interface2, Interface1{
    // Default method
    default void show()
    {
        // Print statement
        System.out.println("Default API");
    }

    @Override
    void display();
}

// Interface 2
// Extending the above interface
interface Interface1 /*extends API*/ {
    // Abstract method
    void display();
}

// Interface 3
// Extending the above interface
interface Interface2 /*extends API*/ {
    // Abstract method
    void print();
    void display();

}

// Main class
// Implementation class code
class TestClass implements API /*Interface1, Interface2*/ {
    // Overriding the abstract method from Interface1
    @Override
    public void display() {
        System.out.println("Display from Interface1");
    }

    // Overriding the abstract method from Interface2
    public void print() {
        System.out.println("Print from Interface2");
    }

    // Main driver method
    public static void main(String args[]) {
        // Creating object of this class
        // in main() method
        TestClass d = new TestClass();


        // Now calling the methods from both the interfaces
        d.show(); // Default method from API
        d.display(); // Overridden method from Interface1
        d.print(); // Overridden method from Interface2
    }
}
//*********************************



// Java Program to Demonstrate
// Method Overloading and Overriding

// Parent Class
class Parent1 {

    // Method Declared
    public void func(){
        System.out.println("Parent Method func");
    }

    // Method Overloading
    public void func(int a){
        System.out.println("Parent Method func " + a);
    }

    public double func(double a){
        System.out.println("Parent Method func LOng" + a);
        return a;
    }
}

// Child Class
class Child1 extends Parent1 {

    // Method Overriding
    @Override
    public void func(int a){
        System.out.println("Child Method " + a);
    }
    @Override
    public double func(double a){
        System.out.println("Child Method LOng" + a);
        return a;
    }

}

// Main Method
 class Main1 {
    public static void main(String args[]){
        Parent1 obj1 = new Parent1();
        obj1.func();
        obj1.func(5);
        obj1.func(50.676);

        Child1 obj2 = new Child1();
        obj2.func(4);
        obj2.func(40.34);
    }
}

/*
// Java program to demonstrate the
// real-world example of Interfaces

//import java.io.*;

interface Vehicle {

    // all are the abstract methods.
    void changeGear(int a);
    void speedUp(int a);
    void applyBrakes(int a);
}

class Bicycle implements Vehicle{

    int speed;
    int gear;

    // to change gear
    @Override
    public void changeGear(int newGear){

        gear = newGear;
    }

    // to increase speed
    @Override
    public void speedUp(int increment){

        speed = speed + increment;
    }

    // to decrease speed
    @Override
    public void applyBrakes(int decrement){

        speed = speed - decrement;
    }

    public void printStates() {
        System.out.println("speed bc: " + speed
                + " gear bc: " + gear);
    }
}

class Bike implements Vehicle {

    int speed;
    int gear;

    // to change gear
    @Override
    public void changeGear(int newGear){

        gear = newGear;
    }

    // to increase speed
    @Override
    public void speedUp(int increment){

        speed = speed + increment;
    }

    // to decrease speed
    @Override
    public void applyBrakes(int decrement){

        speed = speed - decrement;
    }

    public void printStates() {
        System.out.println("speed b : " + speed
                + " gear: b" + gear);
    }

}
class GFG {

    public static void main (String[] args) {

        // creating an instance of Bicycle
        // doing some operations
        Bicycle bicycle = new Bicycle();
        bicycle.changeGear(2);
        bicycle.speedUp(3);
        bicycle.applyBrakes(1);

        System.out.println("Bicycle present state :");
        bicycle.printStates();

        // creating instance of the bike.
        Bike bike = new Bike();
        bike.changeGear(1);
        bike.speedUp(4);
        bike.applyBrakes(3);

        System.out.println("Bike present state :");
        bike.printStates();
    }
}*/


/*

// Java program to demonstrate lambda expressions
// to implement a user defined functional interface.

// A sample functional interface (An interface with
// single abstract method
interface FuncInterface
{
    // An abstract function
    void abstractFun(int x);

    // A non-abstract (or default) function
    default void normalFun()
    {
        System.out.println("Hello");
    }
}

class Test
{
    public static void main(String args[])
    {
        // lambda expression to implement above
        // functional interface. This interface
        // by default implements abstractFun()
        FuncInterface fobj = (int x)->System.out.println(2*x);

        // This calls above lambda expression and prints 10.
        fobj.abstractFun(5);
        fobj.normalFun();
    }
}*/


/*
// A Java program to demonstrate simple lambda expressions
class Test {
    public static void main(String args[])
    {
        // Creating an ArrayList with elements
        // {1, 2, 3, 4}
        ArrayList<Integer> arrL = new ArrayList<Integer>();
        arrL.add(1);
        arrL.add(2);
        arrL.add(3);
        arrL.add(4);

        // Using lambda expression to print all elements
        // of arrL
        arrL.forEach(System.out::println);

        System.out.println("---------------------");
        // Using lambda expression to print even elements
        // of arrL
        arrL.forEach(n -> {
            if (n % 2 == 0)
                System.out.println(n);
        });
    }
}*/
