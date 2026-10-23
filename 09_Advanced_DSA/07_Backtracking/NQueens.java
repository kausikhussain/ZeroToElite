package advanced.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: N-Queens (LeetCode 51 & LeetCode 52).
 * Difficulty: Hard.
 * Pattern: Combinatorial Backtracking with O(1) Column and Diagonal Hashing.
 * 
 * Problem Statement:
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that
 * no two queens attack each other. Given an integer n, return all distinct solutions to
 * the n-queens puzzle. Each solution contains a distinct board configuration of the
 * n-queens' placement, where 'Q' and '.' indicate a queen and an empty space, respectively.
 * 
 * Approach (Row-by-Row DFS with Fast Collision Pruning):
 * - Place queens row by row. Since each row must contain exactly one queen, placing one queen
 *   per row naturally eliminates row conflicts.
 * - For a queen at (r, c):
 *     1. Column conflict: Any queen sharing column c.
 *     2. Main diagonal conflict (\ direction): All cells along this diagonal share the invariant (r - c).
 *        To map to a non-negative index, offset by n: index = (r - c + n).
 *     3. Anti-diagonal conflict (/ direction): All cells along this diagonal share the invariant (r + c).
 *        Range of sum is 0 to 2*n - 2.
 * - Maintain boolean arrays (or bitmasks) for O(1) collision lookups:
 *     - cols[c]
 *     - diag1[r - c + n] (main diagonal \)
 *     - diag2[r + c] (anti-diagonal /)
 * - State Restoration:
 *     - Choose: board[r][c] = 'Q', set cols[c] = diag1[d1] = diag2[d2] = true.
 *     - Explore: recurse to row r + 1.
 *     - Backtrack: reset board[r][c] = '.', set cols[c] = diag1[d1] = diag2[d2] = false.
 * 
 * Time Complexity:
 * - O(N!): The first queen has N choices, second has at most N - 2, third at most N - 4, etc.
 *   Tighter bound than O(N^N).
 * 
 * Space Complexity:
 * - O(N^2) for board representation and O(N) recursion call stack depth plus collision arrays.
 */
public class NQueens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // r - c + n
        boolean[] diag2 = new boolean[2 * n]; // r + c

        backtrack(0, n, board, cols, diag1, diag2, res);
        return res;
    }

    private static void backtrack(int r, int n, char[][] board,
                                  boolean[] cols, boolean[] diag1, boolean[] diag2,
                                  List<List<String>> res) {
        if (r == n) {
            res.add(constructBoard(board));
            return;
        }

        for (int c = 0; c < n; c++) {
            int d1 = r - c + n;
            int d2 = r + c;

            // Collision check in O(1)
            if (cols[c] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // 1. Choose
            board[r][c] = 'Q';
            cols[c] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // 2. Explore
            backtrack(r + 1, n, board, cols, diag1, diag2, res);

            // 3. Un-choose (Backtrack)
            board[r][c] = '.';
            cols[c] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    /**
     * LeetCode 52: Return total count of distinct valid N-Queens configurations.
     */
    public static int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];
        return countSolutions(0, n, cols, diag1, diag2);
    }

    private static int countSolutions(int r, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (r == n) return 1;

        int count = 0;
        for (int c = 0; c < n; c++) {
            int d1 = r - c + n;
            int d2 = r + c;

            if (cols[c] || diag1[d1] || diag2[d2]) {
                continue;
            }

            cols[c] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            count += countSolutions(r + 1, n, cols, diag1, diag2);

            cols[c] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
        return count;
    }

    private static List<String> constructBoard(char[][] board) {
        List<String> list = new ArrayList<>(board.length);
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: N-Queens Verification ===");

        // Test 1: n = 4 (Expected 2 distinct solutions)
        List<List<String>> res4 = solveNQueens(4);
        System.out.println("Test 1: n = 4 Solutions count: " + res4.size() + " (Expected: 2) -> " + (res4.size() == 2 ? "PASS" : "FAIL"));
        for (int i = 0; i < res4.size(); i++) {
            System.out.println(" Solution " + (i + 1) + ":");
            for (String row : res4.get(i)) {
                System.out.println("  " + row);
            }
        }

        // Test 2: n = 1 (Expected 1 solution: ["Q"])
        List<List<String>> res1 = solveNQueens(1);
        boolean pass2 = res1.size() == 1 && res1.get(0).get(0).equals("Q");
        System.out.println("Test 2: n = 1 -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: n = 2 and n = 3 (Expected 0 solutions)
        boolean pass3 = solveNQueens(2).isEmpty() && solveNQueens(3).isEmpty();
        System.out.println("Test 3: n = 2 & n = 3 (no valid placement) -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Total N-Queens counting for n = 8 (Classic 8-Queens: 92 solutions)
        int count8 = totalNQueens(8);
        System.out.println("Test 4: Classic 8-Queens solution count: " + count8 + " (Expected: 92) -> " + (count8 == 92 ? "PASS" : "FAIL"));
    }
}
