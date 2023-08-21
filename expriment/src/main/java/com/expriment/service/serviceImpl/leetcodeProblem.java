package com.expriment.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

public class leetcodeProblem {

  /*  Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000*/
    public static int RomanToInteger(String s) { ///but we need to pass valid roman num here....
        HashMap<Character,Integer> value=new HashMap();
        Integer firstc = null,secondc = null,temp=0;
        try {
            value.put('I',1);value.put('V',5);
            value.put('X',10);value.put('L',50);
            value.put('C',100); value.put('D',500);
            value.put('M',1000);value.put('i',1);value.put('v',5);
            value.put('x',10);value.put('l',50);
            value.put('c',100); value.put('d',500);
            value.put('m',1000);
//            String fst= (s.substring(0,1));
//            String snd= s.substring(1,2);
            int lenghtOfString = s.length()-1;
            if (s.length() ==1) temp+=value.get(s.charAt(0));
            else {
                for (int i = 0; i < lenghtOfString; i++) {
//                do {
                    firstc = value.get(s.charAt(i));
                    secondc = value.get(s.charAt(i + 1));
                    if (firstc >= secondc || secondc == null) {
                        temp = temp + firstc;
                        System.out.println("1 first number " + firstc + "Second num " + secondc + " temp value " + temp);
                    } else /*if (firstc < secondc)*/ {
                        i++;
                        temp = temp + (secondc - firstc);
                        System.out.println("2 first number " + firstc + "Second num " + secondc + " temp value " + temp);
                    }
                    if (i == lenghtOfString - 1 && i != 0) {
                        temp = temp + value.get(s.charAt(lenghtOfString));
                        System.out.println("3 first number " + firstc + "Second num " + secondc + " temp value " + temp);
                    }

                    if (lenghtOfString == 1 && i!=1) temp = temp + value.get(s.charAt(lenghtOfString));
//                    if (i==1) temp = temp + value.get(s.charAt(lenghtOfString));

                    secondc = null;
//                } while (true);
                }

            }System.out.println("temp value "+temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static void main(String[] args) {
//        String s="LXXXIX";
//        String s="XCVII";
//        String s="XCIX";
//        String s="CMLVII";
//        String s="MI";
        String s="Ii";
//        String s="DMLVII";
//        String s="XLV";
        RomanToInteger(s);
    }
}
