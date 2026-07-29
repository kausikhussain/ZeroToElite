package dsa;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 08_DSA_Implementations - Valid Parentheses & Expression Matching
 * 
 * Solves standard stack-based string validation problems:
 * 1. Matching Bracket Validation (isValid)
 * 2. Minimum Parentheses Additions to Make Valid (minAddToMakeValid)
 * 3. Longest Valid Parentheses Substring Length (longestValidParentheses)
 * 
 * Time Complexity: O(N) for all algorithms
 * Space Complexity: O(N) auxiliary space (O(1) for minAddToMakeValid)
 */
public class ValidParentheses {

    /**
     * Checks if a string of brackets '()', '{}', '[]' is valid.
     * Rules:
     * 1. Open brackets must be closed by the same type of brackets.
     * 2. Open brackets must be closed in the correct order.
     * 3. Every close bracket has a corresponding open bracket of the same type.
     */
    public static boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) return false;

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Calculates the minimum number of parentheses additions required to make the string valid.
     * Optimized O(1) auxiliary space implementation.
     */
    public static int minAddToMakeValid(String s) {
        if (s == null || s.isEmpty()) return 0;

        int openNeeded = 0;
        int closeNeeded = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openNeeded++;
            } else if (c == ')') {
                if (openNeeded > 0) {
                    openNeeded--;
                } else {
                    closeNeeded++;
                }
            }
        }
        return openNeeded + closeNeeded;
    }

    /**
     * Finds the length of the longest valid (well-formed) parentheses substring.
     * Uses Stack to track indices of unmatched brackets.
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Base index for length calculation
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println("=== Valid Parentheses & Expression Verification ===");

        // Test 1: Standard Bracket Validation
        boolean test1a = isValid("()[]{}");
        boolean test1b = isValid("{[()]}");
        boolean test1c = !isValid("(]");
        boolean test1d = !isValid("([)]");
        System.out.println("Valid Parentheses Check: " + (test1a && test1b && test1c && test1d));

        // Test 2: Minimum Additions to Make Valid
        int add1 = minAddToMakeValid("())");   // needs 1 '('
        int add2 = minAddToMakeValid("(((");   // needs 3 ')'
        int add3 = minAddToMakeValid("()");    // needs 0
        int add4 = minAddToMakeValid("()))(("); // needs 4
        boolean test2 = (add1 == 1 && add2 == 3 && add3 == 0 && add4 == 4);
        System.out.println("Min Additions Check: " + test2);

        // Test 3: Longest Valid Substring
        int len1 = longestValidParentheses("(()");      // output: 2 ("()")
        int len2 = longestValidParentheses(")()())");   // output: 4 ("()()")
        int len3 = longestValidParentheses("");         // output: 0
        boolean test3 = (len1 == 2 && len2 == 4 && len3 == 0);
        System.out.println("Longest Valid Substring Check: " + test3);

        if (test1a && test1b && test1c && test1d && test2 && test3) {
            System.out.println("\nAll Valid Parentheses tests PASSED successfully!");
        } else {
            System.out.println("\nSome Parentheses tests FAILED.");
        }
    }
}
