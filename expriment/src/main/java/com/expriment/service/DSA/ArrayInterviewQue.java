package com.expriment.service.DSA;

import org.hibernate.criterion.IdentifierProjection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

/*
 * Note:- time Complexity is necessary for product based company interview.
 * */
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
    public int findMissingNumberInArray1ToN(int n){
        int [] arr=new int[n];
        int missing=0;
        for (int i=0; i<n;i++){
            arr[i]=i+1;
//            System.out.println(arr[i]);
        }
        arr[n/2]=0;
        System.out.printf("finding missing number");
        int sum= n*(n+1)/2;
        for (int i=0; i<n; i++){
            sum-=arr[i];
        }
        System.out.println("missing number is "+sum);
        return missing;
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
    * 9) write a program to print the table of 5 with array in java?
    *
    *10) Java Program to print the smallest element in an array
    * 11) Java Program to copy all elements of one array into another array
       12) Java Program to find the frequency of each element in the array
13) Java Program to left rotate the elements of an array
14) Java Program to print the duplicate elements of an array
15) Java Program to print the elements of an array
16) Java Program to print the elements of an array in reverse order
17) Java Program to print the elements of an array present on even position
18) Java Program to print the elements of an array present on odd position
19) Java Program to print the largest element in an array
20) Java Program to print the number of elements present in an array
21) Java Program to print the sum of all the items of the array
22) Java Program to right rotate the elements of an array
    * */

    /* 9) write a program to print the table of 5 with array in java?
    * solving :-
    * */

    public int[] TableOfNumber(int number4Table){
        int tableResult[]=new int[10];
        try {
            System.out.println("Table of "+number4Table +"" +"\n----------");

            for (int i=1 ; i<11; i++){
                tableResult[i-1]= number4Table*i;
                System.out.println("\t"+tableResult[i-1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableResult;

    }

    /*10) Java Program to print the smallest element in an array ?*/

    public int FindSmallestElementOfArray(int [] arr){
        int minValue= Integer.MAX_VALUE;
        System.out.println("minValue :"+minValue);
        try {
            for (int j =0;j<arr.length; j++) {
                if (arr[j] < minValue)
                    minValue = arr[j];
            }
            System.out.println("minValue after :"+minValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minValue;
    }

    /*11) Java Program to copy all elements of one array into another array?*/
    public int [] CopyOneArrayToAnotherArray(int [] dataArr, int [] copyArr){
//        copyArr = new int[dataArr.length];

        try {
            System.out.println("before copy the array:\n");
            for (int i:copyArr) {
                System.out.println(i);
            }
            for (int i=0; i<dataArr.length ; i++){
                copyArr[i]=dataArr[i];
            }
            System.out.println("After copy the array :\n");
            for (int i:copyArr) {
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copyArr;
    }

    /*12) Java Program to find the frequency of each element in the array?*/

    public int [][] FrequencyOfEachElementOfArray(int [] arr){
        int [] fqcArr=new int[arr.length];
        int visited = -1;
        int size=0;
        try {
            for (int i=0; i<arr.length; i++){
                int temp=1;
                for (int j=i+1; j<arr.length; j++){
                    if (arr[i] == arr[j]){
                        temp++;
                        fqcArr[j]=visited;
                    }
                }
                if (fqcArr[i] != visited) {
//                    fqcArr[i][0]=arr[i];
                    fqcArr[i]=temp;
                    size++;
                }
            }
            System.out.println("Frequency of ");
            int [][] retValue = new int[size][2];
            int inc=0;
            for (int i = 0; i < arr.length; i++) {
//                for (int j = 0; j < 2; j++) {
                  if (fqcArr[i] != visited) {
                      System.out.println(arr[i] +" is "+fqcArr[i]+", " +inc);
                      retValue[inc][0]=arr[i];
                      retValue[inc][1]=fqcArr[i];
                      inc++;
                }
            }
            System.out.println("size of retValue row "+ retValue.length);
            return retValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*13) Java Program to left rotate the elements of an array?*/

    public int [] leftRotateOfArrayElement(int [] arr,int n){
        int [] result= new int[arr.length];/*Step 1. make a variable for return the value of array with size equal passed array.*/

        try {
           /* for (int i=0; i< arr.length; i++){
                System.out.printf(arr[i]+" ,");
            }*/
            System.out.println("\n length of Arr "+arr.length+" length of result "+result.length);

            if (n<= arr.length){ /*Step 2. value of N should be less then array size.*/
                for (int i=0; i< arr.length-n; i++){ /* Step 3. update the return array value index 0 to length-n. from passed array.*/
                        result[i]=arr[(n)+i];
//                    System.out.printf(result[i]+" ,");
                }
                for (int j=arr.length-n; j<result.length; j++){ /* Step 4. update teh return array value index from length-n to n*/
                        result[j]=arr[j-(result.length-n)];
//                    System.out.printf(result[j]+" ,");

                }
                System.out.print("\nPassed Array :");
                for (int i=0; i< arr.length; i++)
                    System.out.printf(arr[i]+" ,");

                System.out.print("\nResult Array :");
                for (int i=0; i< result.length; i++)
                    System.out.printf(result[i]+" ,");

            }else {
                System.out.println("Value of N is grater than array length.");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /*Java Program to print the duplicate elements of an array?*/

    public void duplicateElement(int [] arr){
       /* int dup [] = new int[arr.length];
        int visited =-1;
        int count=0;
        for (int i=0; i<arr.length; i++){
            for ( int j=i; j<arr.length; j++){
                if (arr[i] != arr[j]){
                    count++;
                    dup[i]=visited;
                }
            }
            if (dup[i] != visited) {
                dup[i]=count;
            }
        }
        for (int i=0 ;i< arr.length; i++){
            if (dup[i] != -1) System.out.println("duplicate element is "+arr[i]+" times "+dup[i]);
        }*/
        boolean[] visited = new boolean[arr.length];

        System.out.println("Duplicate elements in the array:");

        for (int i = 0; i < arr.length - 1; i++) {
            if (!visited[i]) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] == arr[j]) {
                        System.out.println(arr[i]);
                        visited[j] = true;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        ArrayInterviewQue arrayInterviewQue= new ArrayInterviewQue();
        int[] arr={3,5,22,55,66,2,44,66,1,-33,3,5,66,44,44,2,2,2,2};//,1,1,11,1,111,1,1,1,11,1,1,1,0};
        int [] copyarr = new int[arr.length];
  /*      dublicateElement();
        arrayInterviewQue.findMissingNumberInArray1ToN(100);

        arrayInterviewQue.TableOfNumber(19);
        arrayInterviewQue.FindSmallestElementOfArray(arr);
        arrayInterviewQue.CopyOneArrayToAnotherArray(arr,copyarr);

        int arr1 [][]=arrayInterviewQue.FrequencyOfEachElementOfArray(arr);

        System.out.println("Size fo result array "+arr1[0].length);
        for (int i=0; i<arr1.length; i++){
            System.out.println(" "+arr1[i][0] +"frq is "+ arr1[i][1]);
        } int[] res = arrayInterviewQue.leftRotateOfArrayElement(arr,15);
        System.out.println();
       for (int i=0; i< res.length; i++){
           System.out.printf(res[i]+" ,");
       }*/
        arrayInterviewQue.duplicateElement(arr);
    }
        public String dateFormate(String inputDateString) {

            // Define the input and output formats
//            String inputDateString = "1979-01-01-00:00:00:0";
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss:S");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime dateTime = LocalDateTime.parse(inputDateString, inputFormatter);
            String outputDateString = dateTime.format(outputFormatter);
            System.out.println(inputDateString);

            try {
                // Parse the input string using the input format
//                LocalDateTime dateTime = LocalDateTime.parse(inputDateString, inputFormatter);

                // Format the parsed date to the desired output format
//                String outputDateString = dateTime.format(outputFormatter);

                System.out.println("Converted Date: " + outputDateString);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle parsing exceptions if necessary
            }
            return outputDateString;
        }
}
