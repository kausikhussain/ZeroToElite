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
| [CombinationSum.java](file:///c:/Users/kausi/Desktop/ZeroToElite/09_Advanced_DSA/07_Backtracking/CombinationSum.java) | Combination Sum (LeetCode 39) | **Medium** | Unbounded Candidate Selection & Target Pruning | $O(N^{T/M})$ | $O(T/M)$ |
| [WordSearch.java](file:///c:/Users/kausi/Desktop/ZeroToElite/09_Advanced_DSA/07_Backtracking/WordSearch.java) | Word Search (LeetCode 79) | **Medium** | 2D Grid DFS & In-Place Visited Masking | $O(M \cdot N \cdot 3^L)$ | $O(L)$ |
| [NQueens.java](file:///c:/Users/kausi/Desktop/ZeroToElite/09_Advanced_DSA/07_Backtracking/NQueens.java) | N-Queens (LeetCode 51 / 52) | **Hard** | Row-by-Row DFS & $O(1)$ Diagonal/Column Hashing | $O(N!)$ | $O(N^2)$ |
| [SudokuSolver.java](file:///c:/Users/kausi/Desktop/ZeroToElite/09_Advanced_DSA/07_Backtracking/SudokuSolver.java) | Sudoku Solver (LeetCode 37) | **Hard** | Constraint Propagation DFS & In-Place State Mutation | $O(9^E)$ | $O(E)$ |

---

## ⚡ Combinatorial Complexity Comparison

| Problem | Number of Solutions | Branching Factor | Recurrence / Nature | Complexity |
|---|:---:|:---:|:---:|:---:|
| **Subsets (Power Set)** | $2^N$ | 2 choices per element | $T(N) = 2 \cdot T(N - 1)$ | $O(N \cdot 2^N)$ |
| **Permutations** | $N!$ | $N, N - 1, \dots, 1$ | $T(N) = N \cdot T(N - 1)$ | $O(N \cdot N!)$ |
| **Combination Sum** | Variable | Up to $N$ choices per step | Pruned by target $\le 0$ | $O(N^{T/M})$ |
| **Word Search** | Boolean reachability | Up to 3 per step | 4-directional DFS with visited mask | $O(M \cdot N \cdot 3^L)$ |
| **N-Queens** | Distinct placements | At most $N - 2k$ per row | Row-wise placing with diagonal hashing | $O(N!)$ |
| **Sudoku Solver** | 1 unique completed board | Up to 9 per empty cell | Row/Col/Box constraint-pruned search | $O(9^E)$ ($E \le 81$) |

---

## 💡 Key Architectural Insights

1. **In-Place Array Swapping (Permutations)**:
   Avoids allocating an $O(N)$ boolean array and continuously copying sublists by swapping elements in-place:
   `swap(arr, start, i)` $\rightarrow$ recurse with `start + 1` $\rightarrow$ `swap(arr, start, i)` to restore order.

2. **Sorted Array Early Pruning (Combination Sum)**:
   Sorting candidates upfront allows breaking out of loop immediately once `candidates[i] > target`, saving immense recursion overhead.

3. **In-Place Cell Masking (Word Search)**:
   Temporarily mutating `board[r][c] = '#'` and restoring it on return eliminates the need for an $O(M \times N)$ auxiliary visited matrix, keeping memory consumption strictly $O(L)$ on the recursion call stack.

4. **Diagonal Collision Hashing (N-Queens)**:
   Rather than scanning diagonally across the board in $O(N)$, main diagonals share the constant difference $(r - c)$ and anti-diagonals share the constant sum $(r + c)$, allowing instantaneous $O(1)$ collision validation via index lookup.

5. **Boolean Short-Circuiting & Constraint Propagation (Sudoku Solver)**:
   Returning a boolean flag up the recursion call stack (`if (solve(board)) return true;`) terminates the combinatorial search the exact microsecond the complete valid puzzle is formed, avoiding redundant traversal of sibling branches.



