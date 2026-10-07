package dsa;

import java.util.*;

/**
 * Problem: Implement all foundational tree traversal strategies for a Binary Tree.
 * Concept: Tree Recursion, Breadth-First Search (BFS), Depth-First Search (DFS), Iterative Stack Simulation.
 * 
 * Traversal Types Covered:
 * 1. Breadth-First Search (BFS):
 *    - Level-Order Traversal: Visits nodes layer-by-layer from left to right using a Queue.
 * 
 * 2. Depth-First Search (DFS):
 *    - Pre-Order Traversal  (Root -> Left -> Right): Useful for cloning/serializing trees.
 *    - In-Order Traversal   (Left -> Root -> Right): Produces sorted values in BSTs.
 *    - Post-Order Traversal (Left -> Right -> Root): Useful for bottom-up cleanup, height calculation.
 * 
 * Both RECURSIVE and ITERATIVE versions are provided to teach explicit call-stack management.
 * 
 * Time Complexity:
 * - O(N) for all traversals, where N is the total number of nodes (each node visited once).
 * 
 * Space Complexity:
 * - BFS: O(W) auxiliary space where W is the maximum width of the tree (in a full tree, W = N/2).
 * - DFS: O(H) auxiliary space where H is the height of the tree (O(log N) balanced, O(N) skewed).
 */
public class BinaryTreeTraversals {

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

    // =========================================================================
    // 1. LEVEL-ORDER TRAVERSAL (BFS)
    // =========================================================================

    /**
     * Standard Level-Order Traversal using a Queue.
     * Returns a list of levels, where each sublist contains the values on that level.
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    // =========================================================================
    // 2. PRE-ORDER TRAVERSAL (Root -> Left -> Right)
    // =========================================================================

    public static List<Integer> preOrderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private static void preOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);
        preOrderHelper(node.left, result);
        preOrderHelper(node.right, result);
    }

    public static List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            // Push right child FIRST so that left child is popped and processed first
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

    // =========================================================================
    // 3. IN-ORDER TRAVERSAL (Left -> Root -> Right)
    // =========================================================================

    public static List<Integer> inOrderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    private static void inOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inOrderHelper(node.left, result);
        result.add(node.val);
        inOrderHelper(node.right, result);
    }

    public static List<Integer> inOrderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // Traverse to the leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    // =========================================================================
    // 4. POST-ORDER TRAVERSAL (Left -> Right -> Root)
    // =========================================================================

    public static List<Integer> postOrderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private static void postOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postOrderHelper(node.left, result);
        postOrderHelper(node.right, result);
        result.add(node.val);
    }

    public static List<Integer> postOrderIterative(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        // Preorder is Root -> Left -> Right.
        // If we modify it to Root -> Right -> Left and prepend to result list,
        // we obtain Left -> Right -> Root (Postorder)!
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.addFirst(node.val); // Prepend to beginning

            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return result;
    }

    // =========================================================================
    // 5. TREE METRICS: MAXIMUM DEPTH / HEIGHT
    // =========================================================================

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * Comprehensive test driver verifying traversals on a representative binary tree:
     *            1
     *          /   \
     *         2     3
     *        / \     \
     *       4   5     6
     */
    public static void main(String[] args) {
        System.out.println("=== 08_DSA_Implementations: Binary Tree Traversals Verification ===");

        // Construct tree:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root.right = new TreeNode(3, null, new TreeNode(6));

        // Test 1: BFS Level-Order
        List<List<Integer>> bfs = levelOrder(root);
        System.out.println("BFS Level-Order: " + bfs);
        boolean pass1 = bfs.equals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6)));

        // Test 2: Pre-Order (Recursive & Iterative)
        List<Integer> preRec = preOrderRecursive(root);
        List<Integer> preIter = preOrderIterative(root);
        System.out.println("Pre-Order Recursive: " + preRec);
        System.out.println("Pre-Order Iterative: " + preIter);
        boolean pass2 = preRec.equals(List.of(1, 2, 4, 5, 3, 6)) && preRec.equals(preIter);

        // Test 3: In-Order (Recursive & Iterative)
        List<Integer> inRec = inOrderRecursive(root);
        List<Integer> inIter = inOrderIterative(root);
        System.out.println("In-Order Recursive: " + inRec);
        System.out.println("In-Order Iterative: " + inIter);
        boolean pass3 = inRec.equals(List.of(4, 2, 5, 1, 3, 6)) && inRec.equals(inIter);

        // Test 4: Post-Order (Recursive & Iterative)
        List<Integer> postRec = postOrderRecursive(root);
        List<Integer> postIter = postOrderIterative(root);
        System.out.println("Post-Order Recursive: " + postRec);
        System.out.println("Post-Order Iterative: " + postIter);
        boolean pass4 = postRec.equals(List.of(4, 5, 2, 6, 3, 1)) && postRec.equals(postIter);

        // Test 5: Maximum Depth
        int depth = maxDepth(root);
        System.out.println("Max Depth: " + depth + " (Expected: 3)");
        boolean pass5 = (depth == 3);

        if (pass1 && pass2 && pass3 && pass4 && pass5) {
            System.out.println("\nAll Binary Tree Traversal tests PASSED successfully!");
        } else {
            System.err.println("\nOne or more Binary Tree Traversal tests FAILED.");
        }
    }
}
