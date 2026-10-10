package advanced.trees;

/**
 * Problem: Diameter of Binary Tree (LeetCode 543).
 * Difficulty: Medium.
 * Pattern: Bottom-Up Tree Recursion (Post-Order Aggregation).
 * 
 * Problem Statement:
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 * 
 * Approach:
 * - The diameter passing through any given node as the highest point (turning point) is:
 *   diameterAtNode = leftHeight + rightHeight.
 * - In a bottom-up DFS post-order traversal:
 *   1. Recursively compute leftHeight and rightHeight of children.
 *   2. Update the global maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight).
 *   3. Return the height of current node to its parent: 1 + Math.max(leftHeight, rightHeight).
 * 
 * Key Insight:
 * The longest path does NOT necessarily pass through the root. A deep subtree on one side
 * can contain a larger internal diameter. Post-order DFS allows evaluating every node as a
 * potential turning point while computing height in a single O(N) pass.
 * 
 * Time Complexity:
 * - O(N): Every node is visited exactly once.
 * 
 * Space Complexity:
 * - O(H): Auxiliary recursion stack space, where H is tree height (O(log N) balanced, O(N) worst).
 */
public class DiameterOfBinaryTree {

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

    private static int maxDiameter = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;
    }

    private static int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Path length through current node as turning point
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return height of current subtree to caller
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Diameter of Binary Tree Verification ===");

        // Test 1: Standard tree
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        // Longest path: [4, 2, 1, 3] or [5, 2, 1, 3] -> 3 edges
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        int d1 = diameterOfBinaryTree(root1);
        boolean pass1 = (d1 == 3);
        System.out.println("Test 1 (Standard Tree): Diameter = " + d1 + " (Expected 3) -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Two-node tree
        //   1
        //  /
        // 2 -> 1 edge
        TreeNode root2 = new TreeNode(1, new TreeNode(2), null);
        int d2 = diameterOfBinaryTree(root2);
        boolean pass2 = (d2 == 1);
        System.out.println("Test 2 (Two Nodes): Diameter = " + d2 + " (Expected 1) -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Single node tree -> 0 edges
        TreeNode root3 = new TreeNode(10);
        int d3 = diameterOfBinaryTree(root3);
        boolean pass3 = (d3 == 0);
        System.out.println("Test 3 (Single Node): Diameter = " + d3 + " (Expected 0) -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Diameter of Binary Tree tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
