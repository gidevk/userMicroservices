package com.expriment.Testing.study.streamApis;


import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class java8Stream {

    public static void main(String[] args) {
        JavaStreamTheory javaStreamTheory; //check the stream api theory

        //java 8 have some features like lambda expression, streams, data & time api etc..

        // lambda expression -> it is an anonymous function ( no name, no return type, no access modifier).

        // example of lambda expression

        Thread t1 =  new Thread(new task1()); // this in general

        // now changing same thing in lambda expreation
        Thread t2 = new Thread( ()-> System.out.println("question No 16 "+"Hello"));

        // adding two number throgh lambda expression
        MathOpr add= Integer::sum;
        MathOpr sub= (a,b)->a-b;
        MathOpr mul= (a,b)->a*b;
        MathOpr div= (a,b)->a/b;
        System.out.println("question No lambda "+"value of addition "+add.operation(5,9));
        System.out.println("question No lambda "+"value of subtraction "+sub.operation(5,9));
        System.out.println("question No lambda "+"value of Multiplication "+mul.operation(5,9));
        System.out.println("question No lambda "+"value of division "+div.operation(100,9));

        // predicate  Evaluates this predicate on the given argument.
        //     *
        //     * @param t the input argument
        //     * @return {@code true} if the input argument matches the predicate,
        //     * otherwise {@code false}
         Predicate<Integer> IsEven= x-> x % 2 ==0; // it's holds condition only
        System.out.println("question No lambda "+" Predicated \nisEven  "+ IsEven.test(8));

        Predicate<String> isStartWithInd = x-> x.toLowerCase()
                                                .startsWith("indr");
        System.out.println("question No lambda "+"isStartWithInd "+ isStartWithInd.test("Indradev"));

        // Fucation -Applies this function to the given argument.
        //     *
        //     * @param t the function argument
        //     * @return the function result
        //     */
        //    R apply(T t);
        Function<Integer,Integer> square = x-> x*x;

        System.out.println("question No lambda "+"Function \nSquare "+ square.apply(4));
        Integer apply = square.apply(10);
        System.out.println("question No lambda "+apply);

        //Consumer Performs this operation on the given argument.
        //     *
        //     * @param t the input argument
        //     */
        //    void accept(T t);
        Consumer<Integer> intVal  = System.out::println;

        intVal.accept(200);

        Consumer<Integer> table= x-> {
            for(int i=1 ; i<11; i++){
                System.out.println("question No lambda "+i*x);
            }
        };

//        table.accept(3);


//---------------------------------------------------------------------------
        //Supplier  Gets a result.
        //     *
        //     * @return a result
        //     */
        //    T get();
        Supplier<String> sayHello = ()-> "say Hello";

        String s = sayHello.get();
        System.out.println("question No lambda "+"Supplier_______________             \nSayHello "+ s);


        UnaryOperator<Integer> funcByUna = x-> 2 * x;

        List<String> stringList = Arrays.asList("Ram", "shyam", "mohan");
        stringList.forEach(x-> System.out.println("question No lambda "+x));
        stringList.forEach(System.out::println); // method referance

       List<Student> studentList=  stringList.stream().map(Student::new).collect(Collectors.toList()); // constructor referance

       studentList.forEach(System.out::println);
       studentList.forEach(student -> System.out.println("question No lambda "+student.name));



        /*
---

## 🔰 **Beginner Lambda Expression Programming Questions**

### 1. ✅ **Convert a List of Strings to Uppercase using Lambda**
```java
List<String> list = Arrays.asList("java", "spring", "boot");
```
🧠 *Expected Output:* `["JAVA", "SPRING", "BOOT"]*/
        List<String> list = Arrays.asList("java", "spring", "boot");
        System.out.println("question No 16 "+list.stream().map(String::toUpperCase).collect(Collectors.toList()));

        /*
---

### 2. ✅ **Remove Empty Strings from a List using Lambda**
```java
List<String> input = Arrays.asList("Java", "", "Spring", "", "Boot");
```
🧠 *Expected Output:* `["Java", "Spring", "Boot"]`
*/
        List<String> input = Arrays.asList("Java", "", "Spring", "  ", "Boot");
        System.out.println("question No 16 "+input.stream()
                .filter(x-> (!x.isEmpty())).
                        collect(Collectors.toList()));

        /*
---

### 3. ✅ **Print Even Numbers from a List using Lambda**
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
```
🧠 *Expected Output:* `2, 4, 6`
*/
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println("question No 16 "+nums.stream().filter(x-> x % 2 == 0).collect(Collectors.toList()));


        /*
---

### 4. ✅ **Sort a List of Strings in Reverse Order using Lambda**
```java
List<String> names = Arrays.asList("Zara", "Abhi", "Neha", "Kiran");
```
🧠 *Expected Output:* `[Zara, Neha, Kiran, Abhi]`
*/
        List<String> names = Arrays.asList("Zara", "Abhi", "Neha", "Kiran");
//        List<Integer> names1 = Arrays.asList(1,3,4,22,2,5,500,7);

        System.out.println("question No 16 "+names.stream().sorted().collect(Collectors.toList())); // alphabatical order
        System.out.println("question No 16 "+names.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList())); // reversed order

        /*
---

### 5. ✅ **Count Strings with Length > 3**
```java
List<String> list = Arrays.asList("Java", "is", "awesome", "and", "fast");
```
🧠 *Expected Output:* `3` (`"Java", "awesome", "fast"`)
*/

        List<String> list1 = Arrays.asList("Java", "is", "awesome","awesome", "and", "fast");
        System.out.println("question No 16 "+list1.stream().filter(x-> x.length() >3).count());

        /*
---

## ⚙️ **Intermediate Lambda Questions**

### 6. ⚙️ **Sum All Elements in a List Using Lambda**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
```
🧠 *Expected Output:* `15`
*/
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("question No 16 "+numbers.stream().reduce((a,b)-> a+b).get());

        /*
---

### 7. ⚙️ **Find the Longest String in a List**
```java
List<String> words = Arrays.asList("Java", "SpringBoot", "Microservices", "API");
```
🧠 *Expected Output:* `"Microservices"`
*/
        List<String> words = Arrays.asList("Java", "SpringBoot", "Microservices", "API");
        System.out.println("question No 16 "+words.stream().reduce((a,b)-> a.length() > b.length() ?a:b ).get());

        /*
---

### 8. ⚙️ **Sort a List of Custom Objects by Field (e.g., age)**
```java
List<Employee> list = Arrays.asList(
  new Employee("John", 30),
  new Employee("Sara", 25),
  new Employee("Mark", 35)
);
```
🧠 *Sort by `age` ascending*
*/
        List<Employee> listOfEmp = Arrays.asList(
                new Employee("John", 30),
                new Employee("Sara", 25),
                new Employee("Mark", 35));

//        List<Employee> collect = listOfEmp.stream().sorted(Comparator.comparingInt(e -> e.age)).collect(Collectors.toList());
//        collect.stream().forEach(System.out::println);

        listOfEmp.stream().sorted(Comparator.comparingInt(e -> e.age)).collect(Collectors.toList()).forEach(System.out::println);

        /*
---

### 9. ⚙️ **Find First Element Starting with “S”**
```java
List<String> list = Arrays.asList("Java", "Spring", "Boot", "Security");
```
🧠 *Expected Output:* `"Spring"`
*/
        List<String> list2 = Arrays.asList("Java", "Spring", "Boot", "Security");
        System.out.println("question No 16 "+list2.stream().filter(x-> x.toLowerCase().startsWith("s")).findAny().get());

        /*
---

### 10. ⚙️ **Convert List to Map using Lambda (name → age)**

```java
List<Employee> list = Arrays.asList(
  new Employee("John", 30),
  new Employee("Sara", 25)
);
```
🧠 *Expected Output:* `{John=30, Sara=25}`
*/
        List<Employee> list3 = Arrays.asList(
                new Employee("John", 30),
                new Employee("Sara", 25)
        );

        System.out.println("question No 16 "+"list to Map "+list3.stream().collect(Collectors.toMap(Employee::getName,Employee::getAge)));

        /*
---

## 🚀 **Advanced Lambda Expression Questions**

### 11. 🚀 **Group Employees by Department**
```java
List<Employee> employees = Arrays.asList(
  new Employee("John", "HR"),
  new Employee("Sara", "IT"),
  new Employee("Mark", "IT"),
  new Employee("Tina", "HR")
);
```
🧠 *Expected Output:*
`{HR=[John, Tina], IT=[Sara, Mark]}`

*/
        List<Employees> employees = Arrays.asList(
                new Employees("John", "HR"),
                new Employees("Sara", "IT"),
                new Employees("Mark", "IT"),
                new Employees("Tina", "HR")
        );

//        System.out.println("question No 16 "+"Employee by department "+employees.stream().collect(Collectors.toMap(Employees::getDepartment,Employees::getName)));
        Map<String, List<Employees>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getDepartment()));

//        collect.forEach((dept,name)-> System.out.println("question No 16 "+dept +" "+ ));
        collect.forEach((dept, emps) -> {
            System.out.println("question No 16 "+dept + " = " + emps);
        });


        Map<String, List<String>> collect1 = employees.stream()
                .collect(Collectors.groupingBy(
                        Employees::getDepartment,
                        Collectors.mapping(Employees::getName, Collectors.toList())
                ));

        collect1.forEach((dept, emps) -> {
            System.out.println("question No 16 "+dept + " = " + emps);
        });
        /*

---

### 12. 🚀 **Filter and Sort Employees with Age > 25 and Sort by Name**
```java
List<Employee> employees = Arrays.asList(
  new Employee("John", 30),
  new Employee("Sara", 24),
  new Employee("Alex", 28)
);
```
🧠 *Expected Output:* List of `"Alex", "John"` (filtered and sorted)
*/
        List<Employee> employeeList = Arrays.asList(
                new Employee("John", 30),
                new Employee("Sara", 24),
                new Employee("Alex", 28)
        );

        Map<String, List<Integer>> collect2 = employeeList.stream().filter(x -> x.age > 25).
                collect(Collectors.groupingBy(Employee::getName, Collectors.mapping(Employee::getAge, Collectors.toList())));

        System.out.println("question No 16 "+collect2.toString());

        /*
---

### 13. 🚀 **Find Second Highest Number Using Stream + Lambda**
```java
List<Integer> list = Arrays.asList(10, 30, 20, 40, 50);
```
🧠 *Expected Output:* `40`
*/
        List<Integer> listOfInteger = Arrays.asList(10, 30, 20, 40, 50);
        System.out.println("question No 16 "+listOfInteger.stream().sorted((a,b)->b-a).collect(Collectors.toList()).get(1));

        System.out.println("question No 16 "+listOfInteger.stream().sorted(Collections.reverseOrder()).skip(1).findFirst().get());

        /*
---

### 14. 🚀 **Partition List into Even and Odd Numbers**
```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
```
🧠 *Expected Output:*
`{true=[2, 4, 6], false=[1, 3, 5]}`
*/
        List<Integer> listInt = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> collect3 = listInt.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println("question No 16 "+collect3);

        /*
---

### 15. 🚀 **Find Duplicate Elements in a List**
```java
List<String> list = Arrays.asList("Java", "Python", "Java", "Spring", "Python");
```
🧠 *Expected Output:* `["Java", "Python"]`
*/
        Set<String> seen = new HashSet<>();
        List<String> listOfLang = Arrays.asList("Java", "Python", "Java", "Java", "Java", "Spring", "Python");
        Set<String> collect4 = listOfLang.stream().filter(e -> !seen.add(e)).collect(Collectors.toSet());
        System.out.println("question No 15 "+collect4);


        /*
---

Sure! Continuing from question 15, here are more **Stream API programming questions** starting from **#16**. These questions are especially useful for interviews and cover **real-world patterns, transformations, and data analysis tasks** using Java Streams.

---

### 🔷 **16. Convert a List of Strings to a Map with String and Its Length**
```java
List<String> words = Arrays.asList("Java", "Spring", "Hibernate");
```
🧠 Expected Output:
```java
{Java=4, Spring=6, Hibernate=9}
```
*/
        List<String> wordsofStr = Arrays.asList("Java", "Spring", "Hibernate");
        Map<String, Set<Integer>> collect5 = wordsofStr.stream().collect(Collectors.groupingBy(x -> x, Collectors.mapping(x -> x.length(), Collectors.toSet())));

        System.out.println("question No 16 "+collect5);
        /*
---

### 🔷 **17. Find the Average Age of Employees**
```java
List<Employee> employees = Arrays.asList(
    new Employee("A", 25),
    new Employee("B", 35),
    new Employee("C", 30)
);
```
🧠 Expected Output:
```java
Average: 30.0
```
*/


        List<Employee> employeeAge = Arrays.asList(
                new Employee("A", 25),
                new Employee("B", 35),
                new Employee("C", 30)
        );
        Double collect6 = employeeAge.stream().collect(Collectors.averagingInt(x -> x.age));

        System.out.println("Question NO 17 "+ collect6);


        /*
---

### 🔷 **18. Find Employees Whose Name Starts With ‘A’ and Age > 25**
```java
List<Employee> employees = ...
```
🧠 Use `filter()` and `startsWith()`

*/
        List<Employee> employeeName = Arrays.asList(
                new Employee("Abhi", 25),
                new Employee("Binod", 35),
                new Employee("Chandan", 30),
                new Employee("anand", 26)
        );
        System.out.print("question No 18 ");

        employeeName.stream()
                .filter(x -> x.getName().toLowerCase().startsWith("a") && x.getAge() > 25)
                .collect(Collectors.toList()).forEach(System.out::println);


        /*
---

### 🔷 **19. Return the Names of Top 3 Highest Paid Employees**
```java
List<Employee> employees = ...
```
🧠 Use `sorted()`, `limit(3)`, and `map(Employee::getName)`

---
*/ List<Employee> employeePaid = Arrays.asList(
                new Employee("Abhi", 25),
                new Employee("Binod", 35),
                new Employee("Chandan", 30),
                new Employee("anand", 26)
        );

        List<Employee> collect7 = employeePaid.stream().sorted((e1, e2) -> e2.getAge() - e1.getAge()).limit(3).collect(Collectors.toList());

        System.out.println("Question no 19 "+ collect7);


        /*
### 🔷 **20. Flatten a List of Lists Using FlatMap**
```java
List<List<String>> nested = Arrays.asList(
    Arrays.asList("A", "B"),
    Arrays.asList("C", "D")
);
```
🧠 Output: `["A", "B", "C", "D"]`
*/List<List<String>> nested = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D")
        );




        /*
---

### 🔷 **21. Get the Second Lowest Salary from a List of Employees**
```java
List<Employee> employees = ...
```
🧠 Use `sorted()`, `distinct()`, `skip(1)`, `findFirst()`

---

### 🔷 **22. Count the Number of Employees in Each Department**
```java
Map<String, Long> deptCounts =
    employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));
```

---

### 🔷 **23. Join All Employee Names Into a Single String**
```java
String result = employees.stream()
    .map(Employee::getName)
    .collect(Collectors.joining(", "));
```
🧠 Output: `"John, Sara, Mike"`

---

### 🔷 **24. Find Duplicate Elements in a List of Integers**
```java
List<Integer> list = Arrays.asList(1, 2, 3, 2, 4, 5, 1);
```
🧠 Output: `[2, 1]`

---

### 🔷 **25. Partition a List of Numbers Into Even and Odd**
```java
Map<Boolean, List<Integer>> result =
    list.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
```
🧠 Output:
```java
true  → [2, 4, 6]
false → [1, 3, 5]
```

---

Would you like **code solutions** for any of these, or a **PDF practice set** including answers with explanation?
Would you like full **code solutions** for these, or a PDF practice set for interviews with answers included?
*/


//        swaping two no or string or char
       /* System.out.println("question No 16 "+ "swaping 2, 5 "+swapToNo(2,5));
        System.out.println("question No 16 "+ "swaping 2.1, 5.3 "+swapToNo(2.1,5.3));
        System.out.println("question No 16 "+ "swaping 2.1, 5.3d "+swapToNo(2.1d,5.3d));
        System.out.println("question No 16 "+ "swaping 2.1f, 5.3f "+swapToNo(2.1f,5.3f));
        System.out.println("question No 16 "+ "swaping Hello, world "+swapToNo("hello","world"));
        System.out.println("question No 16 "+ "swaping H, w "+swapToNo('h','w'));*/
    }

    public static  <T> List<T> swapToNo(T t1, T t2){
        List<T> res = new ArrayList<>();
        T t= t1;
        t1=t2;
        t2=t;
        res.add(t1);
        res.add(t2);
        return res;
    }
    private static class Employee {
        String name;
        int age;

        @Override
        public String toString() {
            return "Employee{" +
                    "naem='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Employee(String naem, int age) {
            this.name = naem;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private static class Employees {
        String name;
        String department;

        @Override
        public String toString() {
            return "Employees{" +
                    "name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public Employees(String name, String department) {
            this.name = name;
            this.department = department;
        }
    }
}

class task1 implements Runnable{

    @Override
    public void run() {
        System.out.println("question No 16 "+"Hello");
    }

}

interface MathOpr{
    Integer operation(Integer x, Integer y);
}

class Student{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }
}