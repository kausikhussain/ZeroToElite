package advanced.trees;

/**
 * Problem: Binary Tree Maximum Path Sum (LeetCode 124).
 * Difficulty: Hard.
 * Pattern: Post-Order Branch Gain with Turning Point Optimization.
 * 
 * Problem Statement:
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 
 * Approach:
 * - At any node, the maximum path that has this node as the HIGHEST TURNING POINT is:
 *     currentPath = node.val + leftGain + rightGain
 * - However, to extend a path up to the parent node, the path can only continue along
 *   ONE of the two branches (either left or right, not both).
 * - Therefore, the recursive function returns the maximum single-branch gain to its parent:
 *     branchGain = node.val + Math.max(leftGain, rightGain)
 * - If a subtree gain is negative, we prune it by taking Math.max(0, gain), as adding negative
 *   sums can only reduce the path sum.
 * 
 * Key Mathematical Insight:
 * Distinguish between:
 * 1. The FULL PATH through the node (left branch + node + right branch) -> updates global max.
 * 2. The BRANCH CONTRIBUTION to the ancestor (node + max(left, right)) -> returned to parent.
 * 
 * Time Complexity:
 * - O(N): Each node visited once in bottom-up post-order DFS.
 * 
 * Space Complexity:
 * - O(H): Recursion call stack space.
 */
public class BinaryTreeMaxPathSum {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Max gain from left and right subtrees; ignore negative contributions by bounding with 0
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // The path sum if current node serves as the highest turning point
        int priceNewPath = node.val + leftGain + rightGain;

        // Update overall maximum path sum found so far
        maxSum = Math.max(maxSum, priceNewPath);

        // Return single branch gain to parent
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Binary Tree Max Path Sum Verification ===");

        // Test 1: Simple tree: [1, 2, 3] -> path 2 + 1 + 3 = 6
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        int sum1 = maxPathSum(root1);
        boolean pass1 = (sum1 == 6);
        System.out.println("Test 1 (Tree [1, 2, 3]): Expected 6, Got " + sum1 + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Tree with negative root and deep branch:
        //       -10
        //       /  \
        //      9    20
        //          /  \
        //         15   7
        // Optimal path: 15 + 20 + 7 = 42
        TreeNode root2 = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        int sum2 = maxPathSum(root2);
        boolean pass2 = (sum2 == 42);
        System.out.println("Test 2 (Complex Tree): Expected 42, Got " + sum2 + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: All negative values: [-3] -> expected -3
        TreeNode root3 = new TreeNode(-3);
        int sum3 = maxPathSum(root3);
        boolean pass3 = (sum3 == -3);
        System.out.println("Test 3 (Single Negative [-3]): Expected -3, Got " + sum3 + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Binary Tree Max Path Sum tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
