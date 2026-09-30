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

### 4. Maximum Subarray Sum / Kadane's Algorithm (Medium)
- **Problem**: Write a method `maxSubArray(int[] nums)` that finds the contiguous subarray (containing at least one number) which has the largest sum and returns its sum.
- **Examples**:
  - `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]` -> Output: `6` (subarray `[4, -1, 2, 1]`).
  - `nums = [-1]` -> Output: `-1`.
  - `nums = [5, 4, -1, 7, 8]` -> Output: `23`.
- **Goal**: Implement using **Kadane's Algorithm** (dynamic programming / running sum).
- **Constraint**: $O(n)$ time complexity, $O(1)$ auxiliary space.

### 5. Trapping Rain Water (Hard)
- **Problem**: Write a method `trapRainWater(int[] height)` that computes how much water an elevation map can trap after raining.
- **Examples**:
  - `height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]` -> Output: `6`.
  - `height = [4, 2, 0, 3, 2, 5]` -> Output: `9`.
- **Goal**: Implement using the **Two-Pointer** technique tracking left/right boundaries.
- **Constraint**: $O(n)$ time complexity, $O(1)$ auxiliary space.

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
