# 01_Two_Pointers: The Two-Pointer Paradigm

The **Two-Pointer technique** is a core pattern in algorithmic problem solving where two indices iterate across a collection in tandem to search pairs, intervals, or partitions in linear or near-linear time.

---

## 💡 When to Apply Two Pointers

1. **Sorted Array Pair/Triplet Search**: When the problem asks for pairs meeting an equality or inequality constraint (e.g. `sum == target`), sorting + two pointers replaces $O(N^2)$ brute force with $O(N)$ or $O(N \log N)$.
2. **Bounding / Area Problems**: When calculating water or geometric area, placing pointers at the extremes and greedily moving the limiting boundary yields an optimal $O(N)$ single pass.
3. **In-Place Array Reversals / Partitions**: Reversing arrays, Dutch National Flag partitioning, and palindrome validation.

---

## 🗂️ Problem Directory

| File | Problem | Difficulty | Key Technique | Time | Space |
|---|---|:---:|---|:---:|:---:|
| [TwoSumIISorted.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/01_Two_Pointers/TwoSumIISorted.java) | Two Sum II (Sorted Array) | **Medium** | Opposite Direction Pointers | $O(N)$ | $O(1)$ |
| [ThreeSum.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/01_Two_Pointers/ThreeSum.java) | 3Sum | **Medium** | Sorting + Skip Duplicate Triplet Search | $O(N^2)$ | $O(1)$ |
| [ContainerWithMostWater.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/01_Two_Pointers/ContainerWithMostWater.java) | Container With Most Water | **Medium** | Greedy Outermost Pointers | $O(N)$ | $O(1)$ |

---

## 🔑 Key Invariants to Remember

* **Opposite Direction**:
  - `left = 0`, `right = n - 1`
  - In a sorted array: moving `left` rightward **strictly increases** the sum; moving `right` leftward **strictly decreases** the sum.
* **Greedy Boundary Choice**:
  - In Container With Most Water, always advance the shorter line:
    $$\text{Area} = (r - l) \cdot \min(h[l], h[r])$$
    Moving the taller line shrinks width while area is still bounded by the shorter line, which cannot improve the solution.
