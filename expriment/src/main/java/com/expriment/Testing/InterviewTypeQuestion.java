package com.expriment.Testing.study;

import java.util.*;
import java.util.stream.Collectors;


public class InterviewTypeQuestion {
    public static void main(String[] args) {

//        System.out.println("first non-repeating character "+firstNonREpeatingChar("swiss"));//first non-repeating character w

        /*int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int []arr = {2, 3, -8, 7, -1, 2, 3};

        System.out.println("maxSumOfSubArray "+maxSumOfSubArray(arr));//maxSumOfSubArray 6
*/

 /*       ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(6);

//        ListNode reversed = revItetive(head);
        ListNode reversed = revRecursive(head);
        while (reversed != null) {
            System.out.print(reversed.val + " -> ");
            reversed = reversed.next;
        }
*/

//        System.out.println(" stringParenthesesBalanced "+stringParenthesesBalanced("{(ru[iuyi])}"));
        System.out.println(" stringParenthesesBalanced "+stringParenthesesBalanced("([)]"));
    }

    //  que:   Write a function to find the first non-repeating character in a given string.
    public static Character firstNonREpeatingChar(String str){
        Character response = null;
        Map<Character,Integer> noOfChar = new HashMap<>();
        try {
            for ( char c:str.toCharArray()) {
                noOfChar.put(c,noOfChar.getOrDefault(c,0)+1);
            }

            for (Map.Entry<Character,Integer> entry : noOfChar.entrySet()) {
                if (entry.getValue() == 1)
                    return entry.getKey();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }

    //   que: Find the subarray with the maximum sum using Kadane's Algorithm
    public static int maxSumOfSubArray(int [] arr){
        int curMax= arr[0];
        int max=arr[0];
        for (int i=1; i< arr.length; i++) {
            curMax= Math.max(arr[i],curMax+arr[i]);
            max= Math.max(max,curMax);
        }
        return max;
    }

//    que: Reverse a linked list both iteratively and recursively
    static class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val   = val;
        }
    }
    public static ListNode revItetive(ListNode head){
        ListNode reversedList= null;
        ListNode tempList= head;

        while (tempList != null){
            ListNode temp= tempList.next;
            tempList.next=reversedList;
            reversedList = tempList;
            tempList= temp;
        }
        return reversedList;
    }
    public static ListNode revRecursive(ListNode head){

        if (head == null || head.next ==null){
            System.out.println("Returning node: " + (head != null ? head.val : "null"));
            return head;
        }
        System.out.println("Processing node: " + head.val);
        ListNode recursive= revRecursive(head.next);
        System.out.println("Linking node " + head.next.val + " back to " + head.val);

            head.next.next = head;
            head.next = null;
        return recursive;
    }

//    que:Write a function to check if an input string of parentheses is balanced.

    public static boolean stringParenthesesBalanced(String str){
        Stack<Character> stack=new Stack<>();
        for ( Character c: str.toCharArray()) {
            if (c == '(' || c =='{'|| c == '['){
                stack.push(c);
            }else if(c == ')' || c =='}'|| c == ']' ){
                if (stack.isEmpty())
                    return false;

                char top= stack.pop();

                if ((c == ')' && top != '(' ) ||
                        ( c == '}' && top !='{' )||
                        ( c== ']' && top != '[')){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


}


 class StreamIntermediateOperationsExample {
    public static void main(String[] args) {
        // List of lists of names
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("Reflection", "Collection", "Stream"),
                Arrays.asList("Structure", "State", "Flow"),
                Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
        );

        // Create a set to hold intermediate results
        Set<String> intermediateResults = new HashSet<>();

        // Stream pipeline demonstrating various intermediate operations
        List<String> result = listOfLists.stream()
                .flatMap(List :: stream)// Flatten the list of lists into a single stream
                .filter(s->s.startsWith("S"))// Filter elements starting with "S"
                .map(String :: toUpperCase)// Transform each element to uppercase
                .distinct()// Remove duplicate elements
                .sorted() // Sort elements
                .peek(s->intermediateResults.add(s))// Perform an action (add to set) on each element
                .collect(Collectors.toList());// Collect the final result into a list

        // Print the intermediate results
        System.out.println("Intermediate Results:");
        intermediateResults.forEach(System.out::println);

        // Print the final result
        System.out.println("Final Result:");
        result.forEach(System.out::println);
    }
}

 class StreamTerminalOperationsExample {
    public static void main(String[] args) {
        // Sample data
        List<String> names = Arrays.asList(
                "Reflection", "Collection", "Stream",
                "Structure", "Sorting", "State"
        );

        // forEach: Print each name
        System.out.println("forEach:");
        names.stream().forEach(System.out::println);

        // collect: Collect names starting with 'S' into a list
        List<String> sNames = names.stream()
                .filter(name -> name.startsWith("S"))
                .collect(Collectors.toList());
        System.out.println("\ncollect (names starting with 'S'):");
        sNames.forEach(System.out::println);

        // reduce: Concatenate all names into a single string
        String concatenatedNames = names.stream().reduce(
                "",
                (partialString, element) -> partialString + " " + element
        );
        System.out.println("\nreduce (concatenated names):");
        System.out.println(concatenatedNames.trim());

        // count: Count the number of names
        long count = names.stream().count();
        System.out.println("\ncount:");
        System.out.println(count);

        // findFirst: Find the first name
        Optional<String> firstName = names.stream().findFirst();
        System.out.println("\nfindFirst:");
        firstName.ifPresent(System.out::println);

        // allMatch: Check if all names start with 'S'
        boolean allStartWithS = names.stream().allMatch(
                name -> name.startsWith("S")
        );

        System.out.println("\nallMatch (all start with 'S'):");
        System.out.println(allStartWithS);

        // anyMatch: Check if any name starts with 'S'
        boolean anyStartWithS = names.stream().anyMatch(
                name -> name.startsWith("S")
        );
        System.out.println("\nanyMatch (any start with 'S'):");
        System.out.println(anyStartWithS);
    }
}

class javaTheory{
    /*
Here’s a list of **important OOPs interview questions with answers** tailored for someone with **3+ years of Java experience**.
These questions test both **conceptual understanding** and **real-world application**, which is what most interviews expect at your level.

---

## 🔥 **Top OOPs Interview Questions & Answers in Java (for 3+ Years Experience)**

---

### 1. **What are the main pillars of OOP?**
**Answer:**
1. **Encapsulation** – Wrapping data and methods in a single unit (class), and restricting direct access to internal state.
2. **Abstraction** – Hiding complex implementation details and exposing only essential features.
3. **Inheritance** – A mechanism where one class can inherit fields and methods from another.
4. **Polymorphism** – Ability to perform the same operation in different ways (method overloading and overriding).

---
        ---

        ## 🔰 **The Four Pillars of OOP (Explained with Java Examples)**

        ---

        ### 1. 🔐 **Encapsulation**
        > Bundling the data (fields) and methods (logic) together, and **restricting direct access** to internal state.

        #### 🔸 Real Meaning:
        - Protects internal state of an object.
        - Provides public methods (`getters/setters`) to read/write values.
        - Promotes modular, maintainable code.

        #### ✅ Java Example:
        ```java
        public class Employee {
            private String name; // private = encapsulated

            public String getName() {
                return name;
            }

            public void setName(String name) {
                if(name.length() > 0) {
                    this.name = name;
                }
            }
        }
        ```

        🧠 **Why it matters in real-world code**:
        Encapsulation helps enforce **business rules**, e.g., validating data before it's saved.

        ---

        ### 2. 🧱 **Abstraction**
        > Hiding **complex internal details** and showing **only essential features** to the user.

        #### 🔸 Real Meaning:
        - Define *what* an object can do, but not *how*.
        - Achieved through **interfaces** and **abstract classes**.

        #### ✅ Java Example:
        ```java
        interface Vehicle {
            void start();   // No details, only what it does
        }

        class Car implements Vehicle {
            public void start() {
                System.out.println("Car starts with key or button");
            }
        }
        ```

        🧠 **Why it's useful**:
        Abstraction helps you **work with interfaces** (contracts), so your logic doesn't depend on specific implementations.

        ---

        ### 3. 🧬 **Inheritance**
        > Allows a class (child) to **inherit** fields and methods from another class (parent).

        #### 🔸 Real Meaning:
        - Promotes code reuse.
        - Helps model real-world relationships (IS-A).

        #### ✅ Java Example:
        ```java
        class Animal {
            void sound() {
                System.out.println("Animal makes sound");
            }
        }

        class Dog extends Animal {
            void bark() {
                System.out.println("Dog barks");
            }
        }
        ```

        🧠 **Where you'll use this**:
        Common in **framework development**, domain model hierarchies, and **base service/utility classes**.

        ---

        ### 4. 🔁 **Polymorphism**
        > Means "many forms" — allows you to **perform the same operation differently**.

        #### 🔸 Types in Java:
        - **Compile-time polymorphism** → Method Overloading
        - **Runtime polymorphism** → Method Overriding + dynamic dispatch

        #### ✅ Java Example:
        ```java
        // Overloading
        class Calculator {
            int add(int a, int b) { return a + b; }
            double add(double a, double b) { return a + b; }
        }

        // Overriding
        class Animal {
            void sound() { System.out.println("Animal sound"); }
        }

        class Cat extends Animal {
            void sound() { System.out.println("Meow"); }
        }
        ```

        🧠 **Why it's important**:
        It lets you write **flexible, extensible** code that behaves differently based on the object type.

        -------------------------------------------------------------------------------
                        Absolutely! Polymorphism is one of the most **frequently asked and deeply misunderstood** topics in Java interviews.
                        Since you're 3+ years experienced, the expectation is that you know not just *what* polymorphism is —
                        but also the **different types, practical usage, and how it works internally**.

                ---

                ## 🔁 **Polymorphism in Java – All Possible Interview Scenarios (with Examples)**

                ---

                ### ✅ 1. **Compile-Time Polymorphism (Static Polymorphism / Method Overloading)**

                #### 💡 What is it?
                - Multiple methods with the **same name** but **different signatures**.
                - Decision made at **compile time**.

                #### ✅ Example:

                ```java
                public class Calculator {
                    public int add(int a, int b) {
                        return a + b;
                    }

                    public double add(double a, double b) {
                        return a + b;
                    }

                    public String add(String a, String b) {
                        return a + b;
                    }
                }
                ```

                📌 **Key Point**: JVM picks method based on **number/type of arguments** at **compile time**.

                ---

                ### ✅ 2. **Runtime Polymorphism (Dynamic Polymorphism / Method Overriding)**

                #### 💡 What is it?
                - Child class overrides parent class method.
                - JVM decides **at runtime** which method to call based on **actual object type**.

                #### ✅ Example:

                ```java
                class Animal {
                    void sound() {
                        System.out.println("Animal makes a sound");
                    }
                }

                class Dog extends Animal {
                    @Override
                    void sound() {
                        System.out.println("Dog barks");
                    }
                }

                class Cat extends Animal {
                    @Override
                    void sound() {
                        System.out.println("Cat meows");
                    }
                }

                public class Test {
                    public static void main(String[] args) {
                        Animal a1 = new Dog();  // Polymorphic reference
                        Animal a2 = new Cat();

                        a1.sound();  // Dog barks
                        a2.sound();  // Cat meows
                    }
                }
                ```

                📌 **Key Point**: Method is chosen at **runtime** based on the **actual object**, not the reference type.

                ---

                ### ✅ 3. **Polymorphism via Interfaces**

                #### 💡 What is it?
                - Interface provides **common contract**, different classes **implement differently**.

                #### ✅ Example:

                ```java
                interface Payment {
                    void pay(double amount);
                }

                class CreditCard implements Payment {
                    public void pay(double amount) {
                        System.out.println("Paid " + amount + " via Credit Card");
                    }
                }

                class PayPal implements Payment {
                    public void pay(double amount) {
                        System.out.println("Paid " + amount + " via PayPal");
                    }
                }
                ```

                ```java
                public class Main {
                    public static void main(String[] args) {
                        Payment p1 = new CreditCard();
                        Payment p2 = new PayPal();

                        p1.pay(1000);
                        p2.pay(500);
                    }
                }
                ```

                📌 **Key Point**: Interface enables **behavior-based polymorphism**.

                ---

                ### ✅ 4. **Polymorphism with Abstract Classes**

                ```java
                abstract class Shape {
                    abstract void draw();
                }

                class Circle extends Shape {
                    void draw() {
                        System.out.println("Drawing Circle");
                    }
                }

                class Rectangle extends Shape {
                    void draw() {
                        System.out.println("Drawing Rectangle");
                    }
                }
                ```

                ```java
                public class TestShapes {
                    public static void main(String[] args) {
                        Shape s1 = new Circle();
                        Shape s2 = new Rectangle();
                        s1.draw(); // Drawing Circle
                        s2.draw(); // Drawing Rectangle
                    }
                }
                ```

                📌 **Key Point**: Similar to interfaces, but allows common base functionality as well.

                ---

                ### ✅ 5. **Polymorphism in Collections**

                ```java
                List<String> names = new ArrayList<>();
                Set<Integer> numbers = new HashSet<>();
                Map<Integer, String> users = new HashMap<>();
                ```

                📌 **Key Point**: You work with interface types (`List`, `Set`, `Map`) and use different implementations (`ArrayList`, `HashSet`, etc.).

                ---

                ### ✅ 6. **Constructor Overloading (Compile-Time Polymorphism)**

                ```java
                public class Book {
                    String title;
                    int pages;

                    public Book() {
                        this("Unknown", 0);
                    }

                    public Book(String title) {
                        this(title, 100);
                    }

                    public Book(String title, int pages) {
                        this.title = title;
                        this.pages = pages;
                    }
                }
                ```

                ---

                ### ✅ 7. **Varargs Overloading**

                ```java
                public class VarargsExample {
                    void show(int... a) {
                        System.out.println("Varargs method");
                    }

                    void show(int a) {
                        System.out.println("Single int method");
                    }

                    public static void main(String[] args) {
                        new VarargsExample().show(5); // Single int method
                    }
                }
                ```

                📌 **Key Point**: Shows method resolution preference in overloading.

                ---
                ---

                ## ✅ Can You Override a Static Method in Java?

                > ❌ **No, static methods cannot be overridden.**

                You can **declare a static method in a subclass with the same signature**, but this is called **method hiding**, not overriding.

                ---

                ## ✅ Can You Overload Static Methods?

                > ✅ **Yes, static methods can be overloaded.**
                Just like instance methods, you can define multiple static methods with the **same name but different parameter lists**.

                ---

                ## 🔁 Let's Break It Down with Full Examples

                ---

                ### 🔷 **1. Static Method Overloading (Allowed ✅)**

                ```java
                public class MathUtils {
                    public static int square(int x) {
                        return x * x;
                    }

                    public static double square(double x) {
                        return x * x;
                    }

                    public static int square(int x, int y) {
                        return x * y;
                    }
                }
                ```

                ### 🧪 Usage:

                ```java
                public class TestOverloading {
                    public static void main(String[] args) {
                        System.out.println(MathUtils.square(5));         // 25
                        System.out.println(MathUtils.square(2.5));       // 6.25
                        System.out.println(MathUtils.square(3, 4));      // 12
                    }
                }
                ```

                📌 **Compile-time polymorphism**: Method selected based on **parameter types/count**.

                ---

                ### 🔷 **2. Static Method "Overriding" (Not Allowed ❌, It's Method Hiding)**

                ```java
                class Parent {
                    static void display() {
                        System.out.println("Static method from Parent");
                    }
                }

                class Child extends Parent {
                    static void display() {
                        System.out.println("Static method from Child");
                    }
                }
                ```

                ### 🧪 Usage:

                ```java
                public class TestOverride {
                    public static void main(String[] args) {
                        Parent.display();  // Static method from Parent
                        Child.display();   // Static method from Child

                        Parent p = new Child();
                        p.display();       // ❗ Static method from Parent (not Child!)
                    }
                }
                ```

                📌 Even though `p` refers to a `Child` object, it calls `Parent.display()` because:
                - **Static methods are bound to the class, not the instance.**
                - This is called **method hiding**, not overriding.

                ---

                ## ⚠️ Key Differences: Overloading vs Overriding Static Methods

                | Feature             | Overloading (Static)      | Overriding (Static) |
                |---------------------|---------------------------|----------------------|
                | Allowed?            | ✅ Yes                    | ❌ No (hiding only)  |
                | Binding Time        | Compile-time              | Compile-time         |
                | Inheritance Aware?  | No                        | No                   |
                | Behavior            | Chooses based on signature| Chooses based on reference type (class) |
                | Polymorphism Type   | Compile-time              | Not true polymorphism|

                ---

                ## 🧠 Interview Tip

                If you're asked:

                > **“Can static methods be overridden?”**

                You should say:
                > "No, they can't. Static methods are resolved at compile time and belong to the class, not to instances.
                If a subclass declares a static method with the same signature, it hides the superclass method —
                this is known as **method hiding**, not overriding."

                ---

                Would you like me to prepare a **PDF or code playground repo** with these examples?

                ## 🎯 Bonus: Key Interview Tips

                | Tip # | Insight |
                |-------|---------|
                | ✅    | Always explain *what*, *how*, and *why* when answering. |
                | ✅    | Mention **real-world use cases** (e.g., Spring uses runtime polymorphism heavily). |
                | ⚠️    | Overriding static methods or private methods is **not polymorphism** – it's called **method hiding**. |
                | ❓    | You might be asked: *"Can constructor be overridden?"* → No, because constructors aren't inherited. |

                ---------------------------------------------------------------------------------

        ## 🎯 Real-World Analogy

        Imagine a **TV remote**:
        - You know how to use it (press power, volume up/down) — that’s **abstraction**.
        - The inner wiring and circuits are hidden — that’s **encapsulation**.
        - A **SmartRemote** may extend a **BasicRemote** — that’s **inheritance**.
        - The **power()** button on a remote might work differently for different TV brands — that’s **polymorphism**.

        ---

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
### 2. **What is the difference between abstraction and encapsulation?**
**Answer:**

| Aspect         | Abstraction                        | Encapsulation                         |
|----------------|-------------------------------------|----------------------------------------|
| Purpose        | Hide complexity                    | Hide internal data                     |
| Achieved with  | Interfaces and abstract classes    | `private` access modifier + getters/setters |
| Focus          | What to do                         | How to hide data                       |

---

### 3. **What is method overloading and method overriding?**
**Answer:**

- **Overloading**: Same method name, different parameters, resolved at **compile-time**.
- **Overriding**: Same method signature in child class, resolved at **runtime (dynamic dispatch)**.

---

### 4. **What is polymorphism? How is it implemented in Java?**
**Answer:**
Polymorphism allows objects to be treated as instances of their parent class.
**Types:**
- **Compile-time**: Method overloading
- **Runtime**: Method overriding via inheritance

Java uses **dynamic method dispatch** to implement runtime polymorphism.

---

### 5. **What is the difference between abstract class and interface in Java?**
**Answer:**

| Feature              | Abstract Class                     | Interface                               |
|----------------------|-------------------------------------|------------------------------------------|
| Methods              | Can have both abstract and concrete| Only abstract (until Java 8+ default methods) |
| Multiple Inheritance | Not supported                      | Supported                                |
| Use Case             | Base class with shared logic       | Contract for functionality               |

---

### 6. **What is the use of the `super` keyword in Java?**
**Answer:**
- Access parent class methods and constructors
- Avoid method hiding or name conflict

```java
super(); // calls parent constructor
super.method(); // calls parent method
```

---

### 7. **What is constructor chaining?**
**Answer:**
It’s the process of calling one constructor from another. Done using `this()` (same class) or `super()` (parent class).

---

### 8. **What is the difference between IS-A and HAS-A relationship?**
**Answer:**
- **IS-A** → Inheritance (e.g., `Dog IS-A Animal`)
- **HAS-A** → Composition (e.g., `Car HAS-A Engine`)

---
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         Let’s break them down in depth with examples:

        ---

        ## 🧬 What is an **IS-A Relationship**?

        ### 🔹 Definition:
        An **IS-A** relationship represents **inheritance** or **implementation** — when one class **is a type of** another class or interface.

        ### ✅ Example 1: Inheritance (Class → Class)
        ```java
        class Animal {
            void eat() {
                System.out.println("This animal eats food.");
            }
        }

        class Dog extends Animal {
            void bark() {
                System.out.println("Dog barks.");
            }
        }
        ```

        ### 🔍 Explanation:
        - `Dog IS-A Animal` ✅
        - Because Dog extends Animal, it inherits its behavior → `IS-A` relationship

        ### 🧪 Usage:
        ```java
        Dog d = new Dog();
        d.eat();   // inherited
        d.bark();  // own method
        ```

        ---

        ### ✅ Example 2: Interface Implementation (Class → Interface)

        ```java
        interface Flyable {
            void fly();
        }

        class Bird implements Flyable {
            public void fly() {
                System.out.println("Bird flies.");
            }
        }
        ```

        - `Bird IS-A Flyable` ✅
        - Because Bird implements Flyable

        ---

        ## 🧰 What is a **HAS-A Relationship**?

        ### 🔹 Definition:
        A **HAS-A** relationship represents **composition** or **aggregation** — when one class **contains a reference** to another class **as a field**.

        ### ✅ Example:
        ```java
        class Engine {
            void start() {
                System.out.println("Engine starts.");
            }
        }

        class Car {
            private Engine engine = new Engine();  // HAS-A

            void startCar() {
                engine.start();  // Using the Engine's functionality
                System.out.println("Car is moving...");
            }
        }
        ```

        ### 🔍 Explanation:
        - `Car HAS-A Engine` ✅
        - Car is composed of an Engine object → **composition**

        ---

        ## 🔁 Key Differences Between IS-A and HAS-A

        | Aspect                | IS-A Relationship                        | HAS-A Relationship                       |
        |------------------------|-------------------------------------------|-------------------------------------------|
        | Definition             | Inheritance or Interface implementation   | Composition or Aggregation                |
        | Keyword Used           | `extends` or `implements`                | Instance variable of another class        |
        | Purpose                | Reuse via inheritance                    | Reuse via containing objects              |
        | Real-World Analogy     | A Dog IS-A Animal                        | A Car HAS-A Engine                        |
        | Coupling               | Tightly coupled                          | Loosely coupled (easier to test/mock)     |

        ---

        ## ✅ When to Use What?

        | Use Case                       | Use Relationship |
        |--------------------------------|------------------|
        | One class **is a specialized version** of another | IS-A |
        | One class **uses functionality** of another | HAS-A |

        ---

        ## 🚨 Common Interview Follow-up Question:

        **Q:** *Which is preferred: IS-A or HAS-A?*
        **A:** Generally, **HAS-A (composition)** is preferred over **IS-A (inheritance)** because:
        - It promotes **loose coupling**
        - More **flexible** (e.g., you can easily switch dependencies or mock them in testing)
        - Avoids issues with deep inheritance trees

        This is the famous principle:
        > "Favor composition over inheritance."

        ---

        Let me know if you'd like:
        - Real-world Spring Boot examples of both IS-A and HAS-A
        - Diagram-based explanation or cheat sheet PDF?
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

### 9. **What is object cloning in Java?**
**Answer:**
Creating a copy of an object using the `clone()` method. The class must implement `Cloneable`.

```java
MyObject obj2 = (MyObject) obj1.clone();
```

---

### 10. **How do access modifiers support encapsulation?**
**Answer:**
They control the visibility of variables/methods and enforce data hiding:
- `private`: only within class
- `protected`: class + subclass
- `public`: accessible everywhere
- (default): same package

---

### 11. **What are final, finally, and finalize in Java?**
**Answer:**

| Keyword   | Purpose                            |
|-----------|-------------------------------------|
| `final`   | Prevent inheritance or modification |
| `finally` | Always runs after `try-catch`      |
| `finalize`| Cleanup before garbage collection (deprecated) |

---

### 12. **What is coupling and cohesion in OOP?**
**Answer:**
- **Cohesion**: How well a class does a single task (high = good).
- **Coupling**: Degree of interdependence between classes (low = good).

---

### 13. **Can we override static methods in Java?**
**Answer:**
No. Static methods belong to the class, not instance. So you **can hide**, but not override.

---

### 14. **What is the difference between composition and aggregation?**
**Answer:**
- **Composition**: Strong relationship — contained object cannot exist without the container.
- **Aggregation**: Weak relationship — contained object can exist independently.

---

### 15. **How does Java support abstraction?**
**Answer:**
Using:
- **Interfaces** (100% abstraction)
- **Abstract classes** (partial abstraction)

---

Would you like this in a **PDF format** or a **Spring Boot + OOP real-world scenario** breakdown (e.g., how OOP maps to service, controller, repo)?




    * */

}

class JavaSpringBootQuestion{

    /*
    Below is a curated list of **Infosys interview questions and answers** for a Java developer with 3 years of experience,
    focusing on **Java**, **Spring Boot**, **Microservices**, and **Hibernate**. These questions are derived from real interview sources,
    including platforms like AmbitionBox, GeeksforGeeks, and Glassdoor, as referenced in the provided search results. The answers are tailored to
    reflect the knowledge level expected of a candidate with 3 years of experience, incorporating practical insights and technical accuracy.
    Each question is followed by a concise answer, and citations are included where applicable.

---

### **Java Interview Questions and Answers**

**Q1: What are the key features of Java 8, and how do you use Lambda expressions?**
*Source: AmbitionBox, GeeksforGeeks*  [](https://www.ambitionbox.com/interviews/infosys-interview-questions/java-spring-boot-developer)[](https://www.geeksforgeeks.org/infosys-interview-experience-for-java-backend-developer-3-5-years-experienced/)
**Answer:**
Java 8 introduced several key features, including:
- **Lambda Expressions**: Enable functional programming by allowing you to pass behavior as a parameter.
- **Stream API**: Facilitates functional-style operations on collections (e.g., filtering, mapping).
- **Functional Interfaces**: Interfaces with a single abstract method, like `Predicate` or `Function`.
- **Default Methods**: Allow interfaces to have method implementations.
- **Optional Class**: Helps avoid null pointer exceptions.

**Example of Lambda Expression**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.forEach(n -> System.out.println(n)); // Lambda to print each element
```
In practice, I’ve used lambdas with Streams to filter data, such as selecting users with specific criteria from a list.
**Why Asked**: Infosys often tests Java 8 proficiency, as it’s widely used in their projects.

**Q2: Write a code to filter out loans with incomplete status using Java 8 features.**
*Source: AmbitionBox*  [](https://www.ambitionbox.com/interviews/infosys-interview-questions/java-developer/experienced-candidates)
**Answer:**
Here’s a sample code using Java 8 Streams to filter loans with an "incomplete" status:
```java
import java.util.*;
import java.util.stream.Collectors;

class Loan {
    private String status;
    public Loan(String status) { this.status = status; }
    public String getStatus() { return status; }
}

public class LoanFilter {
    public static void main(String[] args) {
        List<Loan> loans = Arrays.asList(
            new Loan("complete"), new Loan("incomplete"), new Loan("incomplete"), new Loan("complete")
        );

        List<Loan> incompleteLoans = loans.stream()
            .filter(loan -> "incomplete".equalsIgnoreCase(loan.getStatus()))
            .collect(Collectors.toList());

        incompleteLoans.forEach(loan -> System.out.println(loan.getStatus()));
    }
}
```
**Explanation**: The Stream API’s `filter` method uses a lambda to check for "incomplete" status, and `collect` gathers the results into a list. I’ve used this approach in projects to process large datasets efficiently.
**Why Asked**: Tests practical knowledge of Java 8 Streams, a common requirement in Infosys projects.

**Q3: What is the difference between method overloading and method overriding?**
*Source: AmbitionBox*  [](https://www.ambitionbox.com/interviews/infosys-interview-questions/springboot-developer)[](https://blog.internshala.com/infosys-interview-questions-for-java-developer/)
**Answer:**
- **Method Overloading**: Occurs within the same class, where multiple methods have the same name but different parameters (number, type, or order). It’s resolved at compile-time (compile-time polymorphism).
  ```java
  class Calculator {
      int add(int a, int b) { return a + b; }
      double add(double a, double b) { return a + b; }
  }
  ```
- **Method Overriding**: Occurs when a subclass provides a specific implementation of a method defined in its parent class. It’s resolved at runtime (runtime polymorphism).
  ```java
  class Animal {
      void sound() { System.out.println("Generic sound"); }
  }
  class Dog extends Animal {
      @Override
      void sound() { System.out.println("Bark"); }
  }
  ```
**Practical Use**: I’ve used overloading for utility methods to handle different input types and overriding in frameworks like Spring for custom behavior.
**Why Asked**: Tests core OOP concepts, critical for Java-based roles at Infosys.

---

### **Spring Boot Interview Questions and Answers**

**Q4: What is the @RestController annotation in Spring Boot, and how does it differ from @Controller?**
*Source: AmbitionBox, GeeksforGeeks*  [](https://www.ambitionbox.com/interviews/infosys-interview-questions/springboot-developer)[](https://www.geeksforgeeks.org/spring-boot-interview-questions-and-answers/)
**Answer:**
- **@RestController**: A specialized annotation in Spring Boot that combines `@Controller` and `@ResponseBody`. It’s used to build RESTful web services, automatically serializing return objects into JSON/XML.
  ```java
  @RestController
  @RequestMapping("/api")
  public class UserController {
      @GetMapping("/users")
      public List<User> getUsers() {
          return Arrays.asList(new User("John"), new User("Jane"));
      }
  }
  ```
- **@Controller**: Used for traditional web applications, typically returning view names for rendering HTML pages. It requires `@ResponseBody` to return data directly.
**Difference**: `@RestController` simplifies REST API development by eliminating the need for `@ResponseBody` on every method.
**Practical Use**: In my projects, I’ve used `@RestController` to create APIs for microservices, ensuring JSON responses for client applications.
**Why Asked**: Infosys projects heavily use Spring Boot for REST APIs, making this a common question.

**Q5: How would you integrate Spring Boot with a relational database?**
*Source: Hirist*  [](https://www.hirist.tech/blog/top-40-spring-boot-interview-questions/)
**Answer:**
To integrate Spring Boot with a relational database (e.g., MySQL), I follow these steps:
1. **Add Dependencies**: Include `spring-boot-starter-data-jpa` and the database driver (e.g., `mysql-connector-java`) in `pom.xml`.
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
   </dependency>
   ```
2. **Configure Database**: Set up connection properties in `application.properties`.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mydb
   spring.datasource.username=root
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. **Create Entity**: Define a JPA entity class mapped to a database table.
   ```java
   @Entity
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String name;
       // Getters and setters
   }
   ```
4. **Create Repository**: Define a repository interface extending `JpaRepository`.
   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
   }
   ```
5. **Use in Service**: Inject the repository into a service to perform CRUD operations.
**Practical Use**: I’ve integrated Spring Boot with MySQL for user management systems, leveraging Spring Data JPA for simplified database operations.
**Why Asked**: Database integration is a core requirement in Infosys’s enterprise applications.

**Q6: What are Spring Boot profiles, and how do you use them?**
*Source: AmbitionBox*  [](https://www.ambitionbox.com/interviews/infosys-interview-questions)
**Answer:**
Spring Boot profiles allow you to define environment-specific configurations (e.g., dev, test, prod). They are managed via `spring.profiles.active` in `application.properties` or YAML files.
**Example**:
- Create `application-dev.properties` for development:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/dev_db
  ```
- Create `application-prod.properties` for production:
  ```properties
  spring.datasource.url=jdbc:mysql://prod-server:3306/prod_db
  ```
- Activate a profile:
  ```properties
  spring.profiles.active=dev
  ```
  Or via command line: `java -jar app.jar --spring.profiles.active=prod`.
**Practical Use**: In my projects, I’ve used profiles to switch between local and cloud databases, ensuring seamless transitions across environments.
**Why Asked**: Profiles are critical for managing configurations in Infosys’s large-scale applications.

---

### **Microservices Interview Questions and Answers**

**Q7: How do microservices authenticate each other using JWT?**
*Source: AmbitionBox*  [](https://www.ambitionbox.com/interviews/infosys-interview-questions)
**Answer:**
Microservices use **JSON Web Tokens (JWT)** for secure authentication as follows:
1. **Token Generation**: A microservice (e.g., Auth Service) authenticates a user and issues a JWT containing claims (e.g., user ID, roles) signed with a secret key.
2. **Token Propagation**: When one microservice calls another, it includes the JWT in the HTTP request header (e.g., `Authorization: Bearer <token>`).
3. **Token Verification**: The receiving microservice verifies the JWT’s signature using the shared secret key or a public key (for asymmetric signing) and checks claims for authorization.
**Example**:
- Use Spring Security with `jjwt` library to handle JWT:
  ```java
  public String generateToken(String username) {
      return Jwts.builder()
          .setSubject(username)
          .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis() + 86400000))
          .signWith(SignatureAlgorithm.HS512, "secretKey")
          .compact();
  }
  ```
- Verify in another microservice using a filter.
**Practical Use**: I’ve implemented JWT-based authentication in microservices to secure inter-service communication, ensuring only authorized services access protected endpoints.
**Why Asked**: Microservices security is a key focus in Infosys’s distributed systems.

**Q8: What is an API Gateway, and how do you implement it in Spring Boot?**
*Source: GeeksforGeeks*  [](https://www.geeksforgeeks.org/microservices-interview-questions/)
**Answer:**
An **API Gateway** is a single entry point for client requests, routing them to appropriate microservices. It handles cross-cutting concerns like authentication, rate limiting, and monitoring.
**Implementation in Spring Boot**: Use **Spring Cloud Gateway**.
1. Add dependency:
   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-gateway</artifactId>
   </dependency>
   ```
2. Configure routes in `application.yml`:
   ```yaml
   spring:
     cloud:
       gateway:
         routes:
         - id: user-service
           uri: lb://USER-SERVICE
           predicates:
           - Path=/users/**
   ```
3. Enable service discovery with Eureka (optional) for dynamic routing.
**Practical Use**: I’ve used Spring Cloud Gateway to route requests to microservices and enforce authentication via JWT filters.
**Why Asked**: API Gateways are standard in Infosys’s microservices architectures.

---

### **Hibernate Interview Questions and Answers**

**Q9: What is Hibernate caching, and how does it work?**
*Source: AmbitionBox*  [](https://www.ambitionbox.com/interviews/infosys-interview-questions/springboot-developer)
**Answer:**
Hibernate caching improves performance by storing frequently accessed data in memory, reducing database queries. It has two levels:
- **First-Level Cache**: Session-specific, enabled by default. It stores objects within a single session.
  ```java
  Session session = sessionFactory.openSession();
  User user1 = session.get(User.class, 1L); // Fetches from DB
  User user2 = session.get(User.class, 1L); // Fetches from first-level cache
  session.close();
  ```
- **Second-Level Cache**: SessionFactory-scoped, shared across sessions. Requires a provider like Ehcache.
  **Configuration**: Enable in `hibernate.cfg.xml`:
  ```xml
  <property name="hibernate.cache.use_second_level_cache">true</property>
  <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
  ```
  Annotate entities with `@Cacheable` and `@Cache`.
**Practical Use**: I’ve used second-level caching for read-heavy entities like product catalogs to reduce database load.
**Why Asked**: Hibernate is widely used in Infosys projects for ORM, and caching knowledge demonstrates optimization skills.

**Q10: What is the difference between `load()` and `get()` in Hibernate?**
*Source: General Hibernate knowledge, aligned with Infosys expectations*
**Answer:**
- **`session.get(Class, id)`**: Eagerly fetches the entity from the database. If the entity doesn’t exist, it returns `null`.
  ```java
  User user = session.get(User.class, 1L); // Hits DB immediately
  if (user != null) { System.out.println(user.getName()); }
  ```
- **`session.load(Class, id)`**: Uses lazy loading, returning a proxy object without hitting the database until a property is accessed. If the entity doesn’t exist, it throws `ObjectNotFoundException`.
  ```java
  User user = session.load(User.class, 1L); // Returns proxy
  System.out.println(user.getName()); // Hits DB now
  ```
**Practical Use**: I use `get()` for immediate data retrieval and `load()` for scenarios where I may not need to access the entity’s data, optimizing performance.
**Why Asked**: Tests understanding of Hibernate’s loading strategies, crucial for efficient data access.

---

### **Interview Preparation Tips for Infosys**
Based on sources like AmbitionBox and GeeksforGeeks:  [](https://www.geeksforgeeks.org/infosys-interview-experience-for-java-backend-developer-3-5-years-experienced/)[](https://www.ambitionbox.com/interviews/infosys-interview-questions/java-developer/experienced-candidates)
1. **Know Your Resume**: Be prepared to explain all technologies listed, especially Java, Spring Boot, Microservices, and Hibernate.
2. **Focus on Java 8**: Stream API, Lambda expressions, and Functional Interfaces are frequently asked.
3. **Understand Spring Boot Annotations**: Be ready to explain `@RestController`, `@SpringBootApplication`, `@Autowired`, etc.
4. **Microservices Concepts**: Study API Gateway, Circuit Breaker, and JWT authentication.
5. **Hibernate Basics**: Understand caching, lazy vs. eager loading, and JPA integration.
6. **Practice Coding**: Be ready to write code for filtering, sorting, or REST API implementation.
7. **Project Knowledge**: Clearly articulate your role, challenges faced, and solutions implemented in past projects.
8. **Mock Interviews**: Simulate technical interviews to build confidence.

---

### **Why These Questions?**
These questions reflect the technical depth and practical knowledge expected in Infosys interviews for a Java developer with 3 years of experience. They cover core Java (especially Java 8), Spring Boot’s RESTful services and configurations, Microservices architecture (a focus area for Infosys’s enterprise solutions), and Hibernate’s ORM capabilities. The answers incorporate real-world applications to demonstrate hands-on experience, aligning with Infosys’s emphasis on both theoretical and practical skills.[](https://www.geeksforgeeks.org/infosys-interview-experience-for-java-backend-developer-3-5-years-experienced/)[](https://www.ambitionbox.com/interviews/infosys-interview-questions/java-developer/experienced-candidates)

If you need more specific questions or want to dive deeper into any topic, let me know!

    * */
}

class streamApi{

    /*
     filter(Predicate)
 Filters elements based on a condition.
 Example: stream.filter(x -> x > 10);

 map(Function)
 Transforms each element in the stream.
 Example: stream.map(String::toUpperCase);

 flatMap(Function)
 Flattens nested structures like List<List<T>> into a single Stream<T>.
 Example: list.stream().flatMap(List::stream);

 distinct()
 Removes duplicates from the stream.
 Example: stream.distinct();

 sorted()
 Sorts the elements in natural order.
 Example: stream.sorted();

 sorted(Comparator)
 Sorts elements using a custom comparator.
 Example: stream.sorted(Comparator.reverseOrder());

 limit(long)
 Limits the stream to a fixed number of elements.
 Example: stream.limit(5);

 skip(long)
 Skips the first n elements in the stream.
 Example: stream.skip(3);

 peek(Consumer)
 Used for debugging or performing intermediate actions.
 Example: stream.peek(System.out::println);

forEach(Consumer)
 Applies an action to each element.
 Example: stream.forEach(System.out::println);

 toArray()
 Collects elements into an array.
 Example: stream.toArray(String[]::new);

 reduce(BinaryOperator)
 Combines elements into one using an accumulator.
 Example: stream.reduce(Integer::sum);

 collect(Collector)
 Converts the stream into a collection.
 Example: stream.collect(Collectors.toList());

 min(Comparator)
 Finds the minimum element.
 Example: stream.min(Comparator.naturalOrder());

 max(Comparator)
 Finds the maximum element.
 Example: stream.max(Comparator.naturalOrder());

 count()
 Counts the number of elements.
 Example: stream.count();

 anyMatch(Predicate)
 Returns true if any element matches the condition.
 Example: stream.anyMatch(x -> x > 10);

 allMatch(Predicate)
 Returns true if all elements match the condition.
 Example: stream.allMatch(x -> x > 10);

 noneMatch(Predicate)
 Returns true if no elements match the condition.
Example: stream.noneMatch(x -> x < 0);

 findFirst()
 Returns the first element if present.
 Example: stream.findFirst();

 findAny()
 Returns any element if present.
 Example: stream.findAny();

 takeWhile(Predicate)
 Takes elements while condition is true (Java 9+).
 Example: stream.takeWhile(x -> x < 10);

 dropWhile(Predicate)
 Drops elements while condition is true (Java 9+).
 Example: stream.dropWhile(x -> x < 10);

 builder()
 Creates a stream builder.
 Example: Stream.<String>builder().add("a").add("b").build();

 generate(Supplier)
 Creates an infinite stream.
 Example: Stream.generate(Math::random).limit(5);

 iterate(T seed, UnaryOperator)
 Creates an infinite stream with iteration.
 Example: Stream.iterate(0, n -> n + 2).limit(5);

 concat(Stream, Stream)
 Concatenates two streams.
 Example: Stream.concat(stream1, stream2)

    Absolutely! Let's delve into the **Java Stream API**, introduced in Java 8, which provides a powerful and expressive way to process collections of data in a functional style.

---

## 🔹 What is the Stream API?
The Stream API allows you to perform complex data processing operations such as filtering, mapping, and reducing on collections like Lists and Sets It supports both **sequential** and **parallel** execution, enabling efficient utilization of system resources.

---

## 🔸 Stream Lifecycle: Creation → Intermediate Operations → Terminal Operations

### 1. **Stream Creation**
Streams can be created from various data source:

- **From Collections:**
 
```java
  List<String> list = Arrays.asList("a", "b", "c");
  Stream<String> stream = list.stream();
  ``


- **From Arrays:**
 
```java
  String[] array = {"a", "b", "c"};
  Stream<String> stream = Arrays.stream(array);
  ``


- **Using `Stream.of()`:**
 
```java
  Stream<String> stream = Stream.of("a", "b", "c");
  ``


- **Generating Infinite Streams:**
   `Stream.generate()` for generating elements using a Supplie:
    ```java
    Stream<Double> randomNumbers = Stream.generate(Math::random).limit(5);
    ```

   `Stream.iterate()` for iterative generatio:
    ```java
    Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2).limit(5);
    ```

- **From Files:**
 
```java
  Path path = Paths.get("file.txt");
  Stream<String> lines = Files.lines(path);
  ``


---

### 2. **Intermediate Operations**
Intermediate operations transform a stream into another stream. They are lazy and are not executed until a terminal operation is invoke.

- **`filter(Predicate)`** Filters elements based on a conditio.
 
```java
  stream.filter(s -> s.startsWith("a"));
  ``


- **`map(Function)`** Transforms each elemen.
 
```java
  stream.map(String::toUpperCase);
  ``


- **`flatMap(Function)`** Flattens nested structure.
 
```java
  List<List<String>> list = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
  list.stream().flatMap(Collection::stream);
  ``


- **`distinct()`** Removes duplicate element.
 
```java
  stream.distinct();
  ``


- **`sorted()` / `sorted(Comparator)`** Sorts element.
 
```java
  stream.sorted();
  stream.sorted(Comparator.reverseOrder());
  ``


- **`limit(n)`** Limits the stream to the first `n` element.
 
```java
  stream.limit(5);
  ``


- **`skip(n)`** Skips the first `n` element.
 
```java
  stream.skip(5);
  ``


- **`peek(Consumer)`** Performs an action on each element as it passes throug.
 
```java
  stream.peek(System.out::println);
  ``


- **Java 9 Additions:**
  - **`takeWhile(Predicate)`** Takes elements while the condition is tru.
    ```java
    stream.takeWhile(s -> s.length() < 5);
    ```

  - **`dropWhile(Predicate)`** Drops elements while the condition is tru.
    ```java
    stream.dropWhile(s -> s.length() < 5);
    ```

---

### 3. **Terminal Operations**
Terminal operations produce a result or a side-effect and terminate the stream pipelin.

- **`forEach(Consumer)`** Performs an action for each elemen.
 
```java
  stream.forEach(System.out::println);
  ``


- **`toArray()`** Collects elements into an arra.
 
```java
  String[] array = stream.toArray(String[]::new);
  ``


- **`reduce()`** Reduces elements to a single valu.
 
```java
  Optional<String> concatenated = stream.reduce((s1, s2) -> s1 + s2);
  ``


- **`collect(Collector)`** Performs a mutable reduction operatio.
 
```java
  List<String> list = stream.collect(Collectors.toList());
  ``


- **`min(Comparator)` / `max(Comparator)`** Finds the minimum or maximum elemen.
 
```java
  Optional<String> min = stream.min(String::compareTo);
  Optional<String> max = stream.max(String::compareTo);
  ``


- **`count()`** Counts the number of element.
 
```java
  long count = stream.count();
  ``


- **`anyMatch(Predicate)` / `allMatch(Predicate)` / `noneMatch(Predicate)`** Checks for matching element.
 
```java
  boolean anyStartsWithA = stream.anyMatch(s -> s.startsWith("a"));
  boolean allStartsWithA = stream.allMatch(s -> s.startsWith("a"));
  boolean noneStartsWithA = stream.noneMatch(s -> s.startsWith("a"));
  ``


- **`findFirst()` / `findAny()`** Finds an elemen.
 
```java
  Optional<String> first = stream.findFirst();
  Optional<String> any = stream.findAny();



## ✅ **Basic-Level Stream API Interview Questions**

### 1. **What is the Stream API in Java?**
**Answer:**
Stream API, introduced in Java 8, allows functional-style operations on collections. It enables processing of data in a
declarative way using operations like `filter()`, `map()`, `reduce()`, etc.

---

### 2. **What is the difference between a Collection and a Stream?**

| Feature       | Collection                | Stream                     |
|---------------|---------------------------|----------------------------|
| Stores data   | Yes                       | No                         |
| Consumes data | No                        | Yes (streams are consumed) |
| Iteration     | External (loop)           | Internal (functional)      |

---

### 3. **What are the types of Streams in Java?**
- **Sequential Stream**: Processes elements one by one.
- **Parallel Stream**: Divides elements and processes them in multiple threads for performance.

---

### 4. **How do you create a Stream in Java?**
```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();
```

---

### 5. **Can you reuse a Stream once it is consumed?**
**Answer:** No. Streams are single-use. Once a terminal operation is performed, the stream is closed.

---

## 💡 **Intermediate-Level Questions**

### 6. **What is the difference between `map()` and `flatMap()`?**
- `map()` transforms each element.
- `flatMap()` flattens nested structures (e.g., List of Lists).

```java
list.stream().map(x -> x.toUpperCase());
listOfLists.stream().flatMap(List::stream);
```

---

### 7. **What are intermediate and terminal operations?**

- **Intermediate**: `filter()`, `map()`, `sorted()`, `limit()` → returns a Stream.
- **Terminal**: `collect()`, `forEach()`, `reduce()`, `count()` → ends the stream.

---

### 8. **What is the use of the `filter()` method?**
Filters elements based on a condition:

```java
list.stream().filter(x -> x.startsWith("A")).collect(Collectors.toList());
```

---

### 9. **What is the purpose of `reduce()`?**
Used to combine all elements of a stream into a single result.

```java
int sum = list.stream().reduce(0, Integer::sum);
```

---

### 10. **How do you convert a list of strings to uppercase using Stream API?**
```java
list.stream().map(String::toUpperCase).collect(Collectors.toList());
```

---

## 🔥 **Advanced-Level Questions**

### 11. **How do you group elements using Stream API?**
```java
Map<String, List<Employee>> map =
    employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
```

---

### 12. **How do you count the frequency of elements in a list using streams?**
```java
Map<String, Long> count =
    list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
```

---

### 13. **What is the difference between `findFirst()` and `findAny()`?**
- `findFirst()` always returns the first element.
- `findAny()` can return any matching element (especially in parallel streams).

---

### 14. **What are short-circuiting operations in streams?**
Operations like `limit()`, `anyMatch()`, `allMatch()`, `noneMatch()` — they stop processing when a condition is met.

---

### 15. **Can we use Stream API to sort a list of custom objects?**
Yes:

```java
list.stream()
    .sorted(Comparator.comparing(Employee::getSalary))
    .collect(Collectors.toList());
```

---

### 16. **How do you remove duplicates using streams?**
```java
list.stream().distinct().collect(Collectors.toList());
```

---

### 17. **What is lazy evaluation in streams?**
Intermediate operations are not executed until a terminal operation is called — this is called **lazy evaluation**.

---

### 18. **Can Stream be parallel? How?**
Yes:

```java
list.parallelStream().forEach(System.out::println);
```

---

### 19. **Explain `peek()` in Stream API**
Used for debugging or performing intermediate actions without consuming the stream.

```java
list.stream()
    .peek(System.out::println)
    .collect(Collectors.toList());
```

---

### 20. **What are Collectors in Stream API?**
Collectors are used in `collect()` to accumulate stream elements into collections or strings.

Examples:
- `Collectors.toList()`
- `Collectors.joining(", ")`
- `Collectors.groupingBy()`
- `Collectors.mapping()`

---

Would you like this entire Q&A list converted into a **PDF**, or paired with **code snippets or practice exercises**?
    **/
}