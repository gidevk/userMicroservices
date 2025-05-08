package com.expriment.Testing.study.streamApis;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class JavaStreamTheory {

    public static void main(String[] args) {




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

        // supplier

        Supplier<String> giveHellowold = () -> "Hellow world";

        System.out.println(giveHellowold.get());


        //combined Example
        Predicate<Integer> predicate= x-> x %2 ==0;
        Function<Integer,Integer> function= x-> x*x;
        Consumer< Integer>  consumer= x-> System.out.println("Combind Example "+x);
        Supplier<Integer> supplier= ()->100;

        if (predicate.test(supplier.get())){
            consumer.accept(function.apply(supplier.get()));
        }

        // BiPredicate, BiConsumer, BiFunction

        BiPredicate<Integer,Integer> isSumEven = (x,y)-> (x+y) % 2 ==0;
        System.out.println(isSumEven.test(2,8));
        BiConsumer<Integer,String> biConsumer= (x,y)-> {
            System.out.println("biConsumer "+ x);
            System.out.println(y);
        };

        biConsumer.accept(3,"indra");

        BiFunction< String, String, Integer> biFunction= (x,y)->(x+y).length();
        System.out.println("BiFucnction "+biFunction.apply("a","bc"));


        //UnaryOperator

        UnaryOperator<Integer> unany = x-> 2*x;
        BinaryOperator<Integer> binary = (x,y)-> x+y;
        System.out.println(binary.apply(5,3));

        System.out.println(unany.apply(5));
//        ***********************************
        List<Integer> integers = Arrays.asList(23, 31, 43, 55, 22, 93,44, 98,100);

        System.out.println(integers.stream().filter(x->x % 2 ==0).count());
        System.out.println(integers.stream().filter(x->x % 2 ==0).collect(Collectors.toList()));


    }
}
