# 07_Backtracking: State Exploration & Combinatorial Search Patterns

**Backtracking** is an algorithmic technique for solving combinatorial search problems incrementally by building candidate solutions step by step and abandoning ("backtracking" from) a candidate as soon as it is determined that it cannot lead to a valid solution.

---

## 🏛️ The Three Pillars of Backtracking

Every backtracking solution revolves around three fundamental design choices:

1. **Choice**: What decision is made at the current step?
   - In *Subsets*: Decide whether to include `nums[i]` into the current subset candidate.
   - In *Permutations*: Decide which unused element to place at the current position.
2. **Constraint**: Is the current candidate valid, or can branches be pruned early?
   - Pruning saves exponential amounts of useless work.
3. **Goal / Base Case**: When is a candidate added to the final result set?
   - In *Subsets*: Every node in the decision tree represents a valid subset.
   - In *Permutations*: Only leaf nodes where `path.size() == nums.length` represent valid full permutations.

---

## 🔁 The Backtracking Template & State Invariant

The hallmark of backtracking is **state restoration**:

```java
// 1. Choose: alter state to explore branch
path.add(candidate);
used[i] = true;

// 2. Explore: recurse into next decision level
backtrack(nextState, path, res);

// 3. Un-choose (Backtrack): strictly restore state for next sibling branch
path.remove(path.size() - 1);
used[i] = false;
```

Failure to restore state correctly corrupts sibling branches and parent recursion levels.

---

## 📂 Problem Directory

| File | Problem | Difficulty | Key Pattern | Time | Space |
|---|---|:---:|---|:---:|:---:|
| [Subsets.java](file:///c:/Users/kausi/Desktop/ZeroToElite/09_Advanced_DSA/07_Backtracking/Subsets.java) | Subsets (LeetCode 78) | **Medium** | Include / Exclude Decision Tree & Cascading | $O(N \cdot 2^N)$ | $O(N)$ |
| [Permutations.java](file:///c:/Users/kausi/Desktop/ZeroToElite/09_Advanced_DSA/07_Backtracking/Permutations.java) | Permutations (LeetCode 46) | **Medium** | Visited State Array & In-Place Swap | $O(N \cdot N!)$ | $O(N)$ |

---

## ⚡ Combinatorial Complexity Comparison

| Problem | Number of Solutions | Branching Factor | Recurrence | Complexity |
|---|:---:|:---:|:---:|:---:|
| **Subsets (Power Set)** | $2^N$ | 2 choices per element | $T(N) = 2 \cdot T(N - 1)$ | $O(N \cdot 2^N)$ |
| **Permutations** | $N!$ | $N, N - 1, \dots, 1$ | $T(N) = N \cdot T(N - 1)$ | $O(N \cdot N!)$ |

### In-Place Swapping Optimization in Permutations
While a boolean `used` array requires $O(N)$ auxiliary space and list insertions, swapping `nums[start]` with `nums[i]` in-place avoids extra tracking memory and minimizes heap allocation overhead during competitive programming evaluations.
