# 03_Binary_Search_Invariants: Binary Search Mastery

Binary Search is not merely about finding a number in a sorted array; it is a fundamental algorithmic paradigm for **searching any monotonic decision space** in logarithmic time.

---

## 💡 The Three Foundational Paradigms

1. **Boundary Search (First & Last Occurrence / Lower & Upper Bound)**:
   - When duplicate elements exist, standard binary search is insufficient.
   - Adjusting loop conditions (`high = mid - 1` vs `low = mid + 1`) allows converging on the exact left or right boundary of a target range.
2. **Rotated Sorted Arrays**:
   - When a sorted array is shifted by an unknown pivot, dividing at `mid` guarantees that at least one of the two halves remains monotonically sorted.
   - Identifying the sorted half enables logarithmic search without needing to locate the pivot index first.
3. **Binary Search on Answer (Predicate Inversion)**:
   - When direct optimization is hard, formulate a feasibility test: `canAchieve(candidateAnswer) -> boolean`.
   - If the answer space is monotonic (e.g. if speed $K$ works, then speed $K+1$ also works), binary search finds the optimal boundary in $O(N \log M)$ time.

---

## 🗂️ Problem Directory

| File | Problem | Difficulty | Search Paradigm | Time | Space |
|---|---|:---:|---|:---:|:---:|
| [BinarySearchBounds.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/03_Binary_Search_Invariants/BinarySearchBounds.java) | First & Last Position (LeetCode 34) | **Medium** | Left/Right Bound Convergence | $O(\log N)$ | $O(1)$ |
| [SearchRotatedSortedArray.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/03_Binary_Search_Invariants/SearchRotatedSortedArray.java) | Search in Rotated Sorted Array (LeetCode 33) | **Medium** | Sorted-Half Partition Invariant | $O(\log N)$ | $O(1)$ |
| [KokoEatingBananas.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/03_Binary_Search_Invariants/KokoEatingBananas.java) | Koko Eating Bananas (LeetCode 875) | **Medium** | Binary Search on Monotonic Answer | $O(N \log M)$ | $O(1)$ |

---

## 🔑 Crucial Invariant: Midpoint Overflow
Always calculate midpoint using:
$$\text{mid} = \text{low} + \frac{\text{high} - \text{low}}{2}$$
Avoid `(low + high) / 2`, which can overflow 32-bit signed integers when `low + high > Integer.MAX_VALUE`.
