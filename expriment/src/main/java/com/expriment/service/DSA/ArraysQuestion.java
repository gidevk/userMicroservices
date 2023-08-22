package com.expriment.service.DSA;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Stack;

@Service
public class ArraysQuestion {


    public static void main(String[] args) {
        int[] arr= {1,2,3,4,5,6,5,4,3,2,1};
        ArraysQuestion arraysQuestion= new ArraysQuestion();

//        System.out.println("Unique number is "+arraysQuestion.findUniqueNumber(arr));
//        arraysQuestion.linkListExample();
        arraysQuestion.StackExample();
    }
   /*
   * Array is a Linear DAta Structure
   * Elements are stored in contiguous memory locations
   * can access elements randomly using index
   * stores homogeneous elements i.e, somilar elements
   * syntex
   *    Array
   *
   *
   *
   *
   * */
    // finding unpair element in a array by using XOR Operator
    public int findUniqueNumber(int [] arr){
        int res=0;

        for (int i=0; i<arr.length; i++) {
            res= res ^ arr[i];
        }
        return res;

    }

/*
LinkedList
    Java LinkedList class uses a doubly linked list to store the elements. It provides a linked-list data structure.
    It inherits the AbstractList class and implements List and Deque interfaces.

    The important points about Java LinkedList are:

        Java LinkedList class can contain duplicate elements.
        Java LinkedList class maintains insertion order.
        Java LinkedList class is non synchronized.
        In Java LinkedList class, manipulation is fast because no shifting needs to occur.
        Java LinkedList class can be used as a list, stack or queue.
    ----------------------------------------------------------------------------------------------------------------------------------------
    Constructor                             |	Description
    ----------------------------------------------------------------------------------------------------------------------------------------
    LinkedList()	                        |   It is used to construct an empty list.
    LinkedList(Collection<? extends E> c)	|   It is used to construct a list containing the elements of the specified collection, in the order,
                                            |   they are returned by the collection's iterator.
   ----------------------------------------------------------------------------------------------------------------------------------------

        */

    public  void linkListExample(){
        LinkedList<String> ll=new LinkedList<String>();
        System.out.println("Initial list of elements: "+ll);
        ll.add("Ravi");
        ll.add("Vijay");
        ll.add("Ajay");
        System.out.println("After invoking add(E e) method: "+ll);
        //Adding an element at the specific position
        ll.add(1, "Gaurav");
        System.out.println("After invoking add(int index, E element) method: "+ll);
        LinkedList<String> ll2=new LinkedList<String>();
        ll2.add("Sonoo");
        ll2.add("Hanumat");
        //Adding second list elements to the first list
        ll.addAll(ll2);
        System.out.println("After invoking addAll(Collection<? extends E> c) method: "+ll);
        LinkedList<String> ll3=new LinkedList<String>();
        ll3.add("John");
        ll3.add("Rahul");
        //Adding second list elements to the first list at specific position
        ll.addAll(1, ll3);
        System.out.println("After invoking addAll(int index, Collection<? extends E> c) method: "+ll);
        //Adding an element at the first position
        ll.addFirst("Lokesh");
        System.out.println("After invoking addFirst(E e) method: "+ll);
        //Adding an element at the last position
        ll.addLast("Harsh");
        System.out.println("After invoking addLast(E e) method: "+ll);

    }

    /*
    * The stack is a linear data structure that is used to store the collection of objects.
    * It is based on Last-In-First-Out (LIFO). Java collection framework provides many
    * interfaces and classes to store the collection of objects.
    * One of them is the Stack class that provides different operations
    *       such as push, pop, search, etc.
    *
        * The following table shows the different values of the top.
        * ----------------------------------------------------
        * Top Value    |        means
        * ----------------------------------------------------
        *   -1         | stack is empty
        *   0          |    stack has only one element
         *   N-1       |    stack is full
         *   N         |    Stack is overflow
         * -----------------------------------------------------
    *
    *
     * */
    public void StackExample()
    {
//creating an instance of Stack class
        Stack<Integer> stk= new Stack<>();
// checking stack is empty or not
        boolean result = stk.empty();
        System.out.println("Is the stack empty? " + result);
// pushing elements into stack
        stk.push(78);
        stk.push(113);
        stk.push(90);
        stk.push(120);
//prints elements of the stack
        System.out.println("Elements in Stack: " + stk);
        result = stk.empty();
        System.out.println("Is the stack empty? " + result);
        stk.pop();
        System.out.println("After pop Elements from Stack: " + stk);
        stk.peek();
    }
}
