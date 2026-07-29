package dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * 08_DSA_Implementations - Binary Search Tree (BST)
 * 
 * Implements core Binary Search Tree operations:
 * 1. Node Insertion
 * 2. In-Order Traversal (Sorted Output)
 * 3. Element Searching
 * 4. BST Validation (isValidBST)
 * 
 * Time Complexity:
 * - Insert/Search: O(H) where H is tree height (O(log N) average, O(N) worst)
 * - In-Order Traversal: O(N)
 * - BST Validation: O(N)
 * Space Complexity: O(H) for recursion stack
 */
public class BinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Inserts a value into the BST maintaining ordering invariant.
     */
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode current, int val) {
        if (current == null) {
            return new TreeNode(val);
        }
        if (val < current.val) {
            current.left = insertRec(current.left, val);
        } else if (val > current.val) {
            current.right = insertRec(current.right, val);
        }
        return current;
    }

    /**
     * Searches for a value in the BST.
     */
    public boolean search(int val) {
        return searchRec(root, val);
    }

    private boolean searchRec(TreeNode current, int val) {
        if (current == null) return false;
        if (current.val == val) return true;
        return val < current.val ? searchRec(current.left, val) : searchRec(current.right, val);
    }

    /**
     * Performs In-Order Traversal returning values in sorted order.
     */
    public List<Integer> getInOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(TreeNode node, List<Integer> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.val);
            inOrderRec(node.right, result);
        }
    }

    /**
     * Validates whether a binary tree satisfies the BST property.
     * Uses min/max range constraints for safe validation.
     */
    public static boolean isValidBST(TreeNode root) {
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validateBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validateBST(node.left, min, node.val) && validateBST(node.right, node.val, max);
    }

    public TreeNode getRoot() {
        return root;
    }

    public static void main(String[] args) {
        System.out.println("=== Binary Search Tree Demo & Verification ===");

        BinarySearchTree bst = new BinarySearchTree();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            bst.insert(v);
        }

        // Test 1: In-Order Traversal
        List<Integer> inorder = bst.getInOrderTraversal();
        System.out.println("In-Order Traversal: " + inorder);
        boolean test1 = inorder.equals(List.of(20, 30, 40, 50, 60, 70, 80));

        // Test 2: Search Operations
        boolean test2 = bst.search(40) && !bst.search(90);
        System.out.println("Search 40 (true) & Search 90 (false): " + test2);

        // Test 3: BST Validation
        boolean test3 = isValidBST(bst.getRoot());
        System.out.println("Is Valid BST: " + test3);

        // Test 4: Invalid Tree Detection
        TreeNode invalidTree = new TreeNode(50);
        invalidTree.left = new TreeNode(30);
        invalidTree.right = new TreeNode(70);
        invalidTree.left.right = new TreeNode(60); // Invalid node (60 in left subtree of 50)
        boolean test4 = !isValidBST(invalidTree);
        System.out.println("Detect Invalid BST: " + test4);

        if (test1 && test2 && test3 && test4) {
            System.out.println("\nAll Binary Search Tree tests PASSED successfully!");
        } else {
            System.out.println("\nSome BST tests FAILED.");
        }
    }
}
