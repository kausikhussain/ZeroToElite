# Module 08: Data Structures & Algorithms (DSA) Practice Exercises

Practice implementing fundamental data structures, manipulating references/pointers, and custom comparisons.

---

## Exercises

### 1. Easy: Static Array Stack
- **Problem**: Implement a standard LIFO (Last-In-First-Out) Stack of integers using a fixed-size array.
- **Operations**:
  - `void push(int val)`: Adds `val` to the top. Throws/prints overflow if stack is full.
  - `int pop()`: Removes and returns the top value. Throws/returns standard error (e.g. -1) if empty.
  - `int peek()`: Returns the top value without removing it.
  - `boolean isEmpty()`: Checks if the stack is empty.
  - `boolean isFull()`: Checks if the stack is full.
- **Time Complexity**: $O(1)$ for all operations.
- **Space Complexity**: $O(N)$ where $N$ is the max capacity of the array.

### 2. Medium: In-Place Singly Linked List Reversal
- **Problem**: Given the head of a singly linked list, reverse the node pointers in-place so that the head points to the original tail, and return the new head node.
- **Constraints**:
  - You must not modify the `data` values of the nodes. Only alter the `next` pointers.
- **Time Complexity**: $O(N)$ where $N$ is the number of nodes.
- **Space Complexity**: $O(1)$ auxiliary space.

### 3. Hard: Custom Object Sorting (Comparable & Comparator)
- **Problem**: Design a `Student` class with `name` (String), `cgpa` (double), and `age` (int). 
- **Sorting Logic**:
  - Sort a list of `Student` objects primarily by their `cgpa` in **descending** order.
  - If two students have the exact same `cgpa`, resolve ties by sorting them by their `age` in **ascending** order.
- **Techniques**:
  - Use `Comparable<Student>` or a custom `Comparator<Student>`.
- **Time Complexity**: $O(N \log N)$ where $N$ is the number of students.
- **Space Complexity**: $O(N)$ or $O(\log N)$ depending on the sort implementation.
