# 02_Sliding_Window: The Sliding Window Pattern

The **Sliding Window pattern** transforms expensive $O(N \cdot K)$ or $O(N^2)$ nested subarray/substring evaluations into linear $O(N)$ operations by reusing computations across contiguous overlapping ranges.

---

## 💡 Fixed vs Dynamic Sliding Window

```
1. Fixed Window (Size K):
   [i ... i + k - 1] -> [i + 1 ... i + k]
   Recompute in O(1): windowSum += arr[i + k] - arr[i]

2. Dynamic Window (Variable Size):
   - Expand right pointer to include elements until a condition is met/violated.
   - Contract left pointer to restore the invariant or minimize window size.
   - Each element is visited at most twice (once by right, once by left) -> O(N) amortized.
```

---

## 🗂️ Problem Directory

| File | Problem | Difficulty | Window Type | Time | Space |
|---|---|:---:|---|:---:|:---:|
| [MaxSumSubarraySizeK.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/02_Sliding_Window/MaxSumSubarraySizeK.java) | Maximum Sum Subarray Size K | **Easy+** | Fixed Window | $O(N)$ | $O(1)$ |
| [LongestSubstringWithoutRepeating.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/02_Sliding_Window/LongestSubstringWithoutRepeating.java) | Longest Substring Without Repeating | **Medium** | Variable Window + Index Map | $O(N)$ | $O(1)$ |
| [MinimumSizeSubarraySum.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/02_Sliding_Window/MinimumSizeSubarraySum.java) | Minimum Size Subarray Sum | **Medium** | Dynamic Shrinking Window | $O(N)$ | $O(1)$ |

---

## 🔑 Crucial Invariant: Positive Monotonicity
In **Minimum Size Subarray Sum**, the sliding window works in $O(N)$ because all numbers are **strictly positive**. If negative numbers were present, expanding the window could decrease the sum, breaking the monotonic property (which would require a Prefix Sum + Monotonic Deque or Hash Map instead).
