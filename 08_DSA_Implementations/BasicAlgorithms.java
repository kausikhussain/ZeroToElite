package dsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 08_DSA_Implementations - Basic Algorithms & Puzzle Solvers
 * This class gathers standard programming puzzles and their optimal solutions:
 * 1. Armstrong Number Validator
 * 2. Number Reversal
 * 3. Palindrome Validator (for Numbers/Strings)
 * 4. Finding Duplicate Elements in an Array
 * 5. Character Occurrence Counter in a String
 */
public class BasicAlgorithms {

    public static void main(String[] args) {
        System.out.println("=== 1. Armstrong Number Check ===");
        int armNum = 153; // 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
        System.out.println("Is " + armNum + " an Armstrong Number? " + isArmstrong(armNum));
        System.out.println("Is " + 123 + " an Armstrong Number? " + isArmstrong(123));

        System.out.println("\n=== 2. Number Reversal ===");
        int num = 12345;
        System.out.println("Original: " + num + " -> Reversed: " + reverseNumber(num));

        System.out.println("\n=== 3. Palindrome Check ===");
        String word = "radar";
        System.out.println("Is '" + word + "' a palindrome? " + isPalindrome(word));

        System.out.println("\n=== 4. Finding Duplicate Elements ===");
        int[] duplicatesArr = {2, 4, 6, 8, 4, 10, 6, 2};
        findDuplicates(duplicatesArr);

        System.out.println("\n=== 5. Character Occurrence Counter ===");
        String sentence = "Zero To Elite";
        countCharacterOccurrences(sentence);
    }

    /**
     * Verifies if a number matches the sum of cubes of its digits.
     * Time Complexity: O(log10(n)), Space Complexity: O(1)
     */
    public static boolean isArmstrong(int num) {
        int temp = num;
        int sum = 0;
        while (temp > 0) {
            int digit = temp % 10;
            sum += (digit * digit * digit);
            temp /= 10;
        }
        return sum == num;
    }

    /**
     * Reverses integer digit order.
     * Time Complexity: O(log10(n)), Space Complexity: O(1)
     */
    public static int reverseNumber(int num) {
        int reversed = 0;
        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }

    /**
     * Checks if a string reads the same forwards and backwards.
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Locates and prints duplicate values in an array in O(n) time.
     * Uses HashSet to identify duplicates.
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static void findDuplicates(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int val : arr) {
            if (!seen.add(val)) {
                duplicates.add(val);
            }
        }
        System.out.println("Duplicate elements found: " + duplicates);
    }

    /**
     * Computes the frequency of each character in a String.
     * Time Complexity: O(n), Space Complexity: O(k) where k is charset size.
     */
    public static void countCharacterOccurrences(String str) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : str.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        System.out.println("Character frequency count in '" + str + "':");
        counts.forEach((character, count) -> {
            if (character == ' ') {
                System.out.println("[Space]: " + count);
            } else {
                System.out.println("'" + character + "': " + count);
            }
        });
    }
}
