# 07_Competitive_Programming - Practice Exercises

This folder contains competitive programming and advanced data structures exercises: array searching, sliding window algorithms, and heap/priority queue implementations.

## 📝 Exercises

### 1. Contains Duplicate (Easy)
- **Problem**: Write a method `containsDuplicate(int[] nums)` that determines if any value appears at least twice in a given integer array.
- **Goal**: Return `true` if any element is duplicated, otherwise return `false`.
- **Constraint**: Should execute in $O(n)$ time complexity using hash sets.

### 2. Longest Substring Without Repeating Characters (Medium)
- **Problem**: Write a method `lengthOfLongestSubstring(String s)` that finds the length of the longest substring in a given string `s` that does not contain duplicate characters.
- **Examples**:
  - `s = "abcabcbb"` -> Output: `3` (substring `"abc"`).
  - `s = "bbbbb"` -> Output: `1` (substring `"b"`).
  - `s = "pwwkew"` -> Output: `3` (substring `"wke"`).
- **Goal**: Implement using the **Sliding Window** technique with a hash map/set to track characters.
- **Constraint**: $O(n)$ time complexity.

### 3. Merge K Sorted Lists/Arrays (Hard/Challenge)
- **Problem**: Write a method `mergeKSortedArrays(int[][] arrays)` that merges $K$ pre-sorted integer arrays into a single, fully sorted array.
- **Example**:
  - `arrays = [[1, 4, 5], [1, 3, 4], [2, 6]]` -> Output: `[1, 1, 2, 3, 4, 4, 5, 6]`.
- **Goal**: Use a **Min-Heap (PriorityQueue)** to track the smallest element of each array to perform the merge efficiently.
- **Constraint**: $O(N \log K)$ time complexity, where $N$ is the total number of elements across all arrays, and $K$ is the number of arrays.

---

## 🏃 How to Practice
1. Open [CompetitivePractice.java](file:///c:/Users/kausi/Desktop/java/07_Competitive_Programming/CompetitivePractice.java).
2. Complete the methods marked with `TODO`.
3. Run the file to verify if the test drivers execute:
   ```powershell
   javac 07_Competitive_Programming/CompetitivePractice.java
   java competitive.CompetitivePractice
   ```
4. If stuck, check the solution in [solutions/CompetitiveSolutions.java](file:///c:/Users/kausi/Desktop/java/07_Competitive_Programming/solutions/CompetitiveSolutions.java).
