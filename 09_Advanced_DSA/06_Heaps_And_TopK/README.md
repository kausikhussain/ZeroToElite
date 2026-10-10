# 06_Heaps_And_TopK: Heap & Priority Queue Patterns

The **Heap (Priority Queue)** pattern is essential for streaming data, partial sorting, and finding the $K$-th extreme elements without incurring a full $O(N \log N)$ sort.

---

## 💡 The Inverse Size-K Invariant

A fundamental interview insight is knowing whether to use a Min-Heap or a Max-Heap:

* **To find the K Largest elements**: Maintain a **Min-Heap** of size $K$.
  - The root holds the smallest among the top candidates.
  - Any incoming number smaller than the root is ignored; larger numbers evict the root.
  - At the end, the root is the $K$-th largest element.
* **To find the K Smallest elements**: Maintain a **Max-Heap** of size $K$.
* **Two-Heaps Stream Balancing**:
  - `maxHeap` stores the lower half of values.
  - `minHeap` stores the upper half of values.
  - Provides instant $O(1)$ median retrieval during dynamic $O(\log N)$ insertions.

---

## 🗂️ Problem Directory

| File | Problem | Difficulty | Key Pattern | Time | Space |
|---|---|:---:|---|:---:|:---:|
| [KthLargestElement.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/06_Heaps_And_TopK/KthLargestElement.java) | Kth Largest Element (LeetCode 215) | **Medium** | Min-Heap Size-K & QuickSelect | $O(N \log K)$ / $O(N)$ | $O(K)$ / $O(1)$ |
| [TopKFrequentElements.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/06_Heaps_And_TopK/TopKFrequentElements.java) | Top K Frequent Elements (LeetCode 347) | **Medium** | Frequency Min-Heap & Bucket Sort | $O(N \log K)$ / $O(N)$ | $O(N)$ |
| [FindMedianFromDataStream.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/06_Heaps_And_TopK/FindMedianFromDataStream.java) | Find Median from Data Stream (LeetCode 295) | **Hard** | Two-Heaps Partitioning Pattern | $O(\log N)$ add, $O(1)$ query | $O(N)$ |

---

## 🔑 Crucial Invariant: Bucket Sort Linear Optimization
In **Top K Frequent Elements**, while a heap provides $O(N \log K)$, **Bucket Sort** achieves strict $O(N)$ linear time by using the fact that the maximum possible frequency of any element is bounded by the array length $N$.
Bucket indexing `buckets[frequency]` groups elements in $O(N)$ space and time.
