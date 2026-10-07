# 08_DSA_Implementations: Data Structures & Algorithms Foundations

This module contains **from-scratch implementations** of canonical computer science data structures and algorithms in Java. The focus is understanding internal memory models, pointer/reference mechanics, geometric array expansion, and asymptotic complexity invariants without relying blindly on standard library wrappers.

---

## 📚 Concepts Covered

1. **Contiguous vs Linked Memory**:
   - Array-based random access $O(1)$ vs Node-pointer pointer chasing $O(N)$.
   - Dynamic geometric resizing ($2\times$ factor) and amortized analysis.
2. **Divide-and-Conquer Sorting**:
   - Merge Sort: Stable sorting, recurrence relations $T(N) = 2T(N/2) + O(N)$, auxiliary buffer reuse.
   - Quick Sort: In-place partitioning (Lomuto vs Hoare), pivot selection, randomized pivot to avoid $O(N^2)$.
3. **Elementary Sorting**:
   - Bubble Sort ($O(N^2)$), Selection Sort ($O(N^2)$).
4. **Linear Data Structures**:
   - Singly Linked List, Doubly Linked List, Circular Linked List.
   - Array Simple Queue, Circular Queue (modulo arithmetic).
   - Dynamic Array (`DynamicArray<T>`) with automatic shrinkage.
   - MinStack with $O(1)$ constant time minimum element retrieval.
5. **Non-Linear Tree Structures**:
   - Binary Search Tree (BST): Node insertion, search, in-order traversal, valid BST invariant checking.
   - Binary Tree Traversals: Breadth-First Search (Level-Order BFS via Queue), Depth-First Search (Pre-Order, In-Order, Post-Order in both Recursive and Iterative Stack forms).
   - Binary Min/Max Heap: Complete binary tree mapped to flat arrays, $O(\log N)$ sift-up/down, $O(N)$ bottom-up `buildHeap`, and in-place $O(N \log N)$ Heap Sort.
6. **Stack Validation & Expression Parsing**:
   - Valid bracket matching, minimum additions to balance, longest valid parentheses substring.

---

## 🗂️ Implementations Index

| File | Structure / Algorithm | Core Operations | Time Complexity | Space Complexity |
|---|---|---|:---:|:---:|
| [MergeSort.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/MergeSort.java) | Divide & Conquer Sorting | Stable Merge Sort | $O(N \log N)$ (All) | $O(N)$ |
| [QuickSort.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/QuickSort.java) | Divide & Conquer Sorting | Randomized Pivot, Lomuto & Hoare Partition | $O(N \log N)$ avg | $O(\log N)$ stack |
| [DynamicArray.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/DynamicArray.java) | Resizable Array | `add`, `get`, `set`, `remove`, `shrink` | Amortized $O(1)$ add | $O(N)$ |
| [MinStack.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/MinStack.java) | Auxiliary State Stack | `push`, `pop`, `top`, `getMin` | $O(1)$ all | $O(N)$ |
| [BinaryTreeTraversals.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/BinaryTreeTraversals.java) | Binary Tree | BFS Level-Order, DFS Pre/In/Post (Rec + Iter) | $O(N)$ | $O(W)$ BFS, $O(H)$ DFS |
| [BinaryHeap.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/BinaryHeap.java) | Complete Binary Tree / Heap | `insert`, `extractMin`, `buildHeap`, `heapSort` | $O(\log N)$ / $O(N)$ build | $O(1)$ sort aux |
| [SearchingAndSorting.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/SearchingAndSorting.java) | Elementary Search & Sort | Linear Search, Binary Search, Bubble, Selection | $O(N)$ / $O(\log N)$ / $O(N^2)$ | $O(1)$ |
| [SinglyLinkedList.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/SinglyLinkedList.java) | Linked List | `insertBeginning`, `insertEnd`, `deleteValue` | $O(1)$ / $O(N)$ | $O(1)$ aux |
| [DoublyLinkedList.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/DoublyLinkedList.java) | Linked List | Bidirectional pointer navigation | $O(1)$ / $O(N)$ | $O(1)$ aux |
| [CircularLinkedList.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/CircularLinkedList.java) | Linked List | Ring pointer structure | $O(1)$ / $O(N)$ | $O(1)$ aux |
| [Queues.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/Queues.java) | Queue | Array Simple Queue & Modulo Circular Queue | $O(1)$ | $O(N)$ |
| [BinarySearchTree.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/BinarySearchTree.java) | Search Tree | `insert`, `search`, `isValidBST`, In-Order | $O(H)$ avg $O(\log N)$ | $O(H)$ stack |
| [ValidParentheses.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/ValidParentheses.java) | Stack Application | Bracket validation, longest valid substring | $O(N)$ | $O(N)$ |
| [MatrixOperations.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/MatrixOperations.java) | 2D Arrays | Matrix addition & $O(N^3)$ multiplication | $O(R \cdot C)$ / $O(N^3)$ | $O(R \cdot C)$ |
| [BasicAlgorithms.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/08_DSA_Implementations/BasicAlgorithms.java) | Mathematical & Hashing | Armstrong, palindrome, character counting | $O(N)$ | $O(N)$ |

---

## 🎯 Problem Solving Patterns & Invariants

### 1. The Stability Invariant in Sorting
- **Merge Sort**: In `merge(arr, temp, left, mid, right)`, if `temp[i] <= temp[j]`, pick `temp[i]`. The strict equality check ensures stability — objects with identical keys preserve their relative order.
- **Quick Sort**: Partitioning swaps elements across long distances, disrupting stability.

### 2. Geometric Array Resizing (Amortized $O(1)$)
- Incrementing by a fixed constant $K$ results in $\sum_{i=1}^{N/K} (i \cdot K) = O(N^2)$ total work for $N$ additions.
- Doubling capacity ($2\times$) results in $\sum_{i=0}^{\log_2 N} 2^i < 2N = O(N)$ total work, yielding an amortized cost of $\frac{O(N)}{N} = O(1)$ per operation.

### 3. Complete Binary Tree Array Indexing
For node at index $i$ in a 0-indexed array:
$$\text{Parent}(i) = \lfloor\frac{i - 1}{2}\rfloor, \quad \text{Left}(i) = 2i + 1, \quad \text{Right}(i) = 2i + 2$$
- Height of node at index $i$ decreases exponentially towards the bottom.
- Bottom-up `buildHeap` executes in $O(N)$ time because the majority of nodes are near the leaves where sift-down depth is minimal:
$$\sum_{h=0}^{\log N} \frac{N}{2^{h+1}} \cdot h = N \sum_{h=0}^{\infty} \frac{h}{2^{h+1}} = N \cdot 1 = O(N)$$

---

## 🧪 Testing Strategy
All files contain self-contained `main` verification test drivers testing:
1. Normal unsorted arrays
2. Edge cases: Empty collections, single-element collections
3. Arrays with all identical elements
4. Negative values and mixed polarities
5. Already sorted and reverse-sorted collections
6. Out-of-bounds index exceptions

---

## 🔜 Next Topics
- **Fast & Slow Pointers**: Floyd's Cycle Detection on Linked Lists (detecting cycle and cycle origin).
- **From-Scratch Hash Table**: Separate Chaining with linked buckets and dynamic rehashing.
- **Progression to 09_Advanced_DSA**: Two Pointers, Sliding Window, Monotonic Queue, Binary Search on Answer.
