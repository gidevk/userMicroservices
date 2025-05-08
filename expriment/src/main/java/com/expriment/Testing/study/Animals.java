package com.expriment.Testing.study;

import java.util.HashMap;
import java.util.Map;

public class Animals {

    /*
        input 10 white horses, 6 black horses, 5 white cows, 4 black cows, 8 brown cows
        output will be: Output: 15 white animals

    10 black animals

    8 brown animals

    */
    public static void main(String[] args) {
        String input = "10 white horses, 6 black horses, 5 white cows, 4 black cows, 8 brown cows";
        countOfAnimal(input);

    }

    public static void countOfAnimal(String in) {

        Map<String , Integer> count = new HashMap<String, Integer>();
        String[] temp = in.split(",\\s?");
        for (String animal:temp) {
            String [] str2= animal.split(" ");
            Integer num= Integer.parseInt((str2[0].trim()));
            String color= str2[1];

            count.put(color,count.getOrDefault(color,0)+num);

        }
        for (Map.Entry<String,Integer> entry : count.entrySet()) {
            System.out.println(entry.getValue()+ " "+ entry.getKey() +" animals");
        }
    }
}


class AnimalCounter {
    public static void main(String[] args) {
        // Input string
        String input = "10 white horses, 6 black horses, 5 white cows, 4 black cows, 8 brown cows, 2 green goat";

        // Split the input into individual animal entries
        String[] animals = input.split(",\\s*");

        // Map to store color counts
        Map<String, Integer> colorCounts = new HashMap<>();

        // Process each entry
        for (String entry : animals) {
            String[] parts = entry.split(" ");
            int count = Integer.parseInt(parts[0]);
            String color = parts[1];

            // Update count for the color
            colorCounts.put(color, colorCounts.getOrDefault(color, 0) + count);
        }

        // Print the output
        for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey() + " animals");
        }
    }
}
