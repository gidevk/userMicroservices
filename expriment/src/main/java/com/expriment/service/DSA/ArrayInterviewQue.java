package com.expriment.service.DSA;

public class ArrayInterviewQue {
    /*
    * 1. How do you find the missing number in a given integer array of 1 to 100;
    * 2.How do you find the duplicate number on a given integer array?
    * 3. How do you find the largest and smallest number in an unsorted integer array?
    * 4. How do you find all pairs of an integer array whose sum is equal to a given number?
    * 5. How do you find duplication numbers in an array if it contains multiple duplicates?
    * 6. How are duplicates removed from a given array in Java?
    * 7. How is a integer array sorted in place using the quicksort algorithm?
    * 8. How do you remove duplicates from an array in place?
    *
    * */

//        * 1. How do you find the missing number in a given integer array of 1 to 100;

    public static void main(String[] args) {
        dublicateElement();
    }
    public static void dublicateElement(){
        int [] arr={1,2,7,8,1};
        for (int i= 0 ; i<arr.length; i++){
            int mainValue = arr[i];
            for (int j=i+1;j<arr.length; j++) {

                int nextValue = arr[j];
                if (mainValue == nextValue) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            }
        }
    }
    /*
    * task
    * 1. write a program to print the table of 5 with array in java?
    * 2.
    * 3.
    *
    *
    * 1) Java Program to copy all elements of one array into another array
2) Java Program to find the frequency of each element in the array
3) Java Program to left rotate the elements of an array
4) Java Program to print the duplicate elements of an array
5) Java Program to print the elements of an array
6) Java Program to print the elements of an array in reverse order
7) Java Program to print the elements of an array present on even position
8) Java Program to print the elements of an array present on odd position
9) Java Program to print the largest element in an array
10) Java Program to print the smallest element in an array
11) Java Program to print the number of elements present in an array
12) Java Program to print the sum of all the items of the array
13) Java Program to right rotate the elements of an array
    * */
}
