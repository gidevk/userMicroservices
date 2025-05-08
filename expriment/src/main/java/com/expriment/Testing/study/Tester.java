package com.expriment.Testing.study;

import java.util.*;

public class Tester {
    /*public static void main(String[] args) {
        String str= "abc";
        String permutations[] = findPermutations(str);
        for (String permutation: permutations)
            if (permutation != null)
                System.out.println(permutation);
    }
    public static String[] findPermutations(String str){
        Set<String> result = new HashSet<>();  // To store unique permutations if necessary
        permute(new StringBuilder(str),0,result);
        return result.toArray(new String[result.size()]);
    }
    public static void permute(StringBuilder str, int idx, Set<String> result){
        int size=str.length()-1;
        if (idx== size) result.add(str.toString());

        for (int i = idx; i <= size; i++) {
            swap(str, idx, i);// Swap the current character with the leftmost character
            permute(str, idx + 1,result);// Recursively generate permutations for the rest of the string
            swap(str, idx, i); // Backtrack to restore the original string before swapping the next character
        }
    }
    private static void swap(StringBuilder s, int i, int j) {
        char temp = s.charAt(i);
        s.setCharAt(i, s.charAt(j));
        s.setCharAt(j, temp);
    }*/


      /*  public static void main(String[] args) {
        double[] inArr= {23500.0,25080.0,28760.0,22340.0,19890.0};
        double[] outArr=findDetails(inArr);
        System.out.println("Average salary:"+ outArr[0]);
        System.out.println("Number of salaries grater than the average salary: "+ outArr[1]);
        System.out.println("Number of salaries lesser than the average salary: "+ outArr[2]);

    }
    public static double[] findDetails(double [] inArr){
        double[] outArr= new double[3];
        double sumOfSalary=0, avgSal =0;
        double nOSalLessAvg=0.0,nOSalGraterAvg=0.0;
        for (int i=0 ; i<inArr.length; i++)
            sumOfSalary+=inArr[i];

        avgSal= sumOfSalary/(inArr.length);

        for (int j=0; j<inArr.length; j++){
            if (avgSal< inArr[j]) nOSalLessAvg++;
            else nOSalGraterAvg ++;
        }
        outArr[0]=avgSal;
        outArr[1]= nOSalLessAvg;
        outArr[2]=nOSalGraterAvg;

        return outArr;
    }*/
    //******************************************************************************************8
/*    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] inArr = new int[size];
        int[] outArr = new int[size];
        for (int index = 0; index<size; index++)
            inArr[index]= sc.nextInt();
        sc.close();
        outArr=findPrimList(inArr);

        for (int index = 0; index<size; index++){
            if (outArr[index] !=0)
                System.out.println(outArr[index]);
        }

    }
    public static int[] findPrimList(int [] inArr){
        int[] outArr= new int[inArr.length];

        for (int index = 0; index<inArr.length; index++ ) {
            if (isPrime(inArr[index])) outArr[index]=inArr[index];
            else outArr[index]=0;
        }
        return outArr;
    }
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++)
            if (number % i == 0)
                return false;
        return true;
    }*/





    /*
    *******************************************************************
    *******************************************************************
    *******************************************************************
    *******************************************************************
    ********************************************************************/

    public int mostFrequentElement(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        int mostFrequent = arr[0];
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequent = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostFrequent;
    }

    public static void main(String[] args) {
        String str= "AbcdcbAb";
        System.out.println(" String is " + str+" isPanlindrum "+ isPanlindum(str));
    }
    public static Boolean isPanlindum(String str){
        int left=0, right = str.length() -1;

        while (left< right) {
            if (str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}

//***********************************************************************************************
/*class Room {
    private int roomNo;
    private int capacity;
    private int roomCounter;

    public Room(int roomNo, int capacity) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.roomCounter = 0;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRoomCounter() {
        return roomCounter;
    }

    public void setRoomCounter(int roomCounter) {
        this.roomCounter = roomCounter;
    }
}

class Member {
    private int memberId;
    private String name;
    private Room room;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

class Admin {
    public void assignRoom(Room[] roomarr, Member member) {
        Room room = null;

        for (int i = 0; i < roomarr.length; i++) {
            if (roomarr[i].getCapacity() == roomarr[i].getRoomCounter()) ;
            else { room = roomarr[i];
                break;
            }
        }
//        Room room = roomarr[i];
        if (room.getRoomCounter() < room.getCapacity()) {
            member.setRoom(room);
            room.setRoomCounter(room.getRoomCounter() + 1);
            System.out.println("Hi "+member.getName()+ "! Your room number is " + room.getRoomNo());
        } else {
            System.out.println("Room " + room.getRoomNo() + " is full.");
        }
    }
}


class HostelApp {
    public static void main(String[] args) {
        Room room1 = new Room(500, 4);
        Room room2 = new Room(501, 4);
        Room room3 = new Room(502, 4);
        Room room4 = new Room(503, 4);
        Room room5 = new Room(504, 4);
        Room[] roomArr={room1,room2,room3,room4,room5};

        Member member1 = new Member(101, "Serena");
        Member member2 = new Member(102, "Martha");
        Member member3 = new Member(103, "Nila");
        Member member4 = new Member(104, "Maria");
        Member member5 = new Member(105, "Eva");

        Admin admin = new Admin();
        admin.assignRoom(roomArr, member1);
        admin.assignRoom(roomArr, member2);
        admin.assignRoom(roomArr, member3);
        admin.assignRoom(roomArr, member4);
        admin.assignRoom(roomArr, member5);
    }*/


//**********************************************************************
