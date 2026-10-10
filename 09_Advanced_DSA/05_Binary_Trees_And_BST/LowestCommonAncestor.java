package advanced.trees;

/**
 * Problem: Lowest Common Ancestor of a Binary Tree (LeetCode 236).
 * Difficulty: Medium.
 * Pattern: Post-Order Tree Search and Branch Aggregation.
 * 
 * Problem Statement:
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 'p' and 'q'.
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself)."
 * 
 * Approach:
 * - Base cases:
 *   1. If root is null, return null.
 *   2. If root == p or root == q, return root (found target node).
 * - Recurse down both branches:
 *   TreeNode left = lowestCommonAncestor(root.left, p, q);
 *   TreeNode right = lowestCommonAncestor(root.right, p, q);
 * - Aggregation:
 *   1. If both left and right are non-null:
 *      One target is in the left subtree, and the other is in the right subtree.
 *      Therefore, the current root is their lowest common ancestor!
 *   2. If only one branch returned a non-null node:
 *      Propagate that non-null node upwards (either LCA is already found or both nodes lie in that subtree).
 * 
 * Key Insight:
 * Post-order traversal bubbles results from leaves up to the root. The first node where
 * both the left and right subtrees report finding one of the targets is uniquely the LCA.
 * 
 * Time Complexity:
 * - O(N): Visits every node at most once in the worst case.
 * 
 * Space Complexity:
 * - O(H): Recursion call stack space, where H is tree height.
 */
public class LowestCommonAncestor {

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

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If p and q were found in different subtrees, root is the LCA
        if (left != null && right != null) {
            return root;
        }

        // Otherwise return the non-null result (or null if neither found)
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Lowest Common Ancestor Verification ===");

        // Construct tree:
        //             3
        //           /   \
        //          5     1
        //         / \   / \
        //        6   2 0   8
        //           / \
        //          7   4
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2, n7, n4);
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5, n6, n2);

        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n1 = new TreeNode(1, n0, n8);

        TreeNode root = new TreeNode(3, n5, n1);

        // Test 1: LCA of 5 and 1 is 3 (different subtrees)
        TreeNode lca1 = lowestCommonAncestor(root, n5, n1);
        boolean pass1 = (lca1 == root);
        System.out.println("Test 1 (LCA of 5 and 1): Expected 3, Got " + (lca1 != null ? lca1.val : null) + " -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: LCA of 5 and 4 is 5 (one node is ancestor of the other)
        TreeNode lca2 = lowestCommonAncestor(root, n5, n4);
        boolean pass2 = (lca2 == n5);
        System.out.println("Test 2 (LCA of 5 and 4): Expected 5, Got " + (lca2 != null ? lca2.val : null) + " -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: LCA of 6 and 4 is 5
        TreeNode lca3 = lowestCommonAncestor(root, n6, n4);
        boolean pass3 = (lca3 == n5);
        System.out.println("Test 3 (LCA of 6 and 4): Expected 5, Got " + (lca3 != null ? lca3.val : null) + " -> " + (pass3 ? "PASS" : "FAIL"));

        if (pass1 && pass2 && pass3) {
            System.out.println("\nAll Lowest Common Ancestor tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more tests FAILED.");
        }
    }
}
