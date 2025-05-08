package com.expriment.Testing.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntQuestion {
    public static void main(String[] args) {

        int [] nums= {1,2,3,4,5,6,7,8};
        System.out.println("result ");
        List<int[]> res=findPairs(nums, 9);
        System.out.println("Pairs of indices and their values that sum to 9:");
        for (int[] pair : res ) {
            int index1 = pair[0];
            int index2 = pair[1];
            int value1 = nums[index1];
            int value2 = nums[index2];

            System.out.println("Indices: [" + index1 + ", " + index2 + "], Values: [" + value1 + ", " + value2 + "]");
        }
       /* for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).toString());
        }*/
//        res.stream().flatMap(x->).forEach(System.out::println);
    }

    public static List<int[]> findPairs(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement), i});
//                System.out.println(result.get());
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
