# 04_Monotonic_Stack: The Monotonic Stack Pattern

A **Monotonic Stack** is a specialized stack that preserves its elements in strictly monotonic (increasing or decreasing) order. It is the premier data structure for resolving **"Next/Previous Greater/Smaller Element"** queries in linear $O(N)$ time.

---

## 💡 Monotonic Increasing vs Decreasing

| Stack Type | Ordering Invariant | Used For | Example Problem |
|---|---|---|---|
| **Monotonic Decreasing** | Top of stack is the smallest; values decrease upwards. | Next Greater Element, Daily Temperatures | Popping elements when a greater value appears. |
| **Monotonic Increasing** | Top of stack is the largest; values increase upwards. | Next Smaller Element, Largest Rectangle in Histogram | Popping elements when a smaller value appears. |

---

## 🗂️ Problem Directory

| File | Problem | Difficulty | Stack Type | Time | Space |
|---|---|:---:|---|:---:|:---:|
| [NextGreaterElement.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/04_Monotonic_Stack/NextGreaterElement.java) | Next Greater Element I & II (Circular) | **Medium** | Monotonic Decreasing | $O(N)$ | $O(N)$ |
| [DailyTemperatures.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/04_Monotonic_Stack/DailyTemperatures.java) | Daily Temperatures (LeetCode 739) | **Medium** | Monotonic Decreasing (Indices) | $O(N)$ | $O(N)$ |
| [LargestRectangleInHistogram.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/04_Monotonic_Stack/LargestRectangleInHistogram.java) | Largest Rectangle in Histogram (LeetCode 84) | **Hard** | Monotonic Increasing (Indices) | $O(N)$ | $O(N)$ |

---

## 🔑 Crucial Invariant: Amortized $O(N)$ Complexity
Although the inner `while` loop pops multiple items, **every index is pushed onto the stack exactly once and popped at most once**.
Across the entire iteration of $N$ elements, the total number of push operations is $N$ and the total number of pop operations is at most $N$, resulting in an amortized $O(N)$ overall runtime.
