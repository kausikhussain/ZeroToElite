# 05_Collections_Framework - Practice Exercises

This folder contains exercises to practice using the Java Collections Framework: Lists, Sets, Maps, Queues, and custom cache architectures.

## 📝 Exercises

### 1. Word Frequency Counter (Easy)
- **Problem**: Write a method `countWordFrequency(String text)` that:
  - Takes a block of text, splits it into words (ignoring case and punctuation).
  - Counts the frequency of each word.
  - Returns a `Map<String, Integer>` containing word counts.
- **Constraints**: $O(n)$ time complexity where $n$ is the word count.

### 2. De-duplicate List with Order Retention (Medium)
- **Problem**: Write a method `removeDuplicatesKeepOrder(List<Integer> list)` that:
  - Receives an `ArrayList` containing duplicate values.
  - Removes all duplicates, returning a collection containing only unique elements.
  - **Constraint**: Must maintain the exact original insertion order. Do not sort or shuffle the order.
  - **Performance**: Should run in $O(n)$ time.

### 3. Design an LRU (Least Recently Used) Cache (Hard/Challenge)
- **Problem**: Design and implement a data structure for a Least Recently Used (LRU) Cache:
  - Create a class `LRUCache` initialized with a positive `capacity`.
  - Implement `get(int key)`: Returns the value of the key if it exists, otherwise returns `-1`. Marks the key as recently used.
  - Implement `put(int key, int value)`: Inserts or updates the value of the key. If the cache reaches its capacity, it must evict the least recently used item before inserting the new item.
- **Complexity Goal**: Both operations should ideally run in $O(1)$ time.

---

## 🏃 How to Practice
1. Open [CollectionsPractice.java](file:///c:/Users/kausi/Desktop/java/05_Collections_Framework/CollectionsPractice.java).
2. Complete the classes and methods marked with `TODO`.
3. Run the file to verify if the test drivers execute:
   ```powershell
   javac 05_Collections_Framework/CollectionsPractice.java
   java collections.CollectionsPractice
   ```
4. If stuck, check the solution in [solutions/CollectionsSolutions.java](file:///c:/Users/kausi/Desktop/java/05_Collections_Framework/solutions/CollectionsSolutions.java).
