package com.expriment.Testing.study;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCountWithStream {
    public static void main(String[] args) {
        String input = "example string";

        // Count characters using Stream API
        Map<Character, Long> charCount = input.chars()
            .mapToObj(c -> (char) c) // Convert int to char
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        /*   input.chars()
                                                    .mapToObj(ch ->(char)ch)
                                                    .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));*/
        // Print the character count
        charCount.forEach((character, count) -> 
            System.out.println(character + ": " + count));
    }
}
