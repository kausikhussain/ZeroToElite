# 05_Binary_Trees_And_BST: Tree Structural & Path Patterns

Tree problems in interview rounds almost universally hinge on mastering **Post-Order bottom-up DFS recursion**, where subtrees solve subproblems independently before the parent combines their results.

---

## 💡 The Post-Order Turning Point Paradigm

When searching for optimal paths or diameters in a tree:
1. **The Turning Point**: A node where a path changes direction from ascending to descending.
2. **Branch Contribution**: A parent can only incorporate ONE child branch into a continuous path extending to higher ancestors.
3. **Local Max vs Returned Value**:
   - Update global maximum with: $\text{node.val} + \text{leftGain} + \text{rightGain}$
   - Return to parent: $\text{node.val} + \max(\text{leftGain}, \text{rightGain})$

---

## 🗂️ Problem Directory

| File | Problem | Difficulty | Key Pattern | Time | Space |
|---|---|:---:|---|:---:|:---:|
| [DiameterOfBinaryTree.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/05_Binary_Trees_And_BST/DiameterOfBinaryTree.java) | Diameter of Binary Tree (LeetCode 543) | **Medium** | Bottom-up Height Aggregation | $O(N)$ | $O(H)$ |
| [LowestCommonAncestor.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/05_Binary_Trees_And_BST/LowestCommonAncestor.java) | Lowest Common Ancestor (LeetCode 236) | **Medium** | Post-order Branch Intersection | $O(N)$ | $O(H)$ |
| [BinaryTreeMaxPathSum.java](file:///c:/Users/kausi/Desktop/My%20Learning/java/09_Advanced_DSA/05_Binary_Trees_And_BST/BinaryTreeMaxPathSum.java) | Max Path Sum (LeetCode 124) | **Hard** | Branch Gain Pruning & Peak Optimization | $O(N)$ | $O(H)$ |

---

## 🔑 Crucial Invariant: Pruning Negative Gains
In **Binary Tree Maximum Path Sum**, if a child branch has a negative total sum, taking that branch reduces the total path value.
Always prune negative subtrees using:
$$\text{gain} = \max(0, \text{maxGain}(\text{child}))$$
