package advanced.backtracking;

/**
 * Problem: Word Search (LeetCode 79).
 * Difficulty: Medium.
 * Pattern: 2D Grid Backtracking with DFS & In-Place State Mutation.
 * 
 * Problem Statement:
 * Given an m x n grid of characters 'board' and a string 'word', return true if 'word' exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a single word.
 * 
 * Approach (2D Grid DFS with In-Place Visited Masking):
 * - Iterate through every cell (r, c) on the board as a potential starting letter.
 * - When board[r][c] matches word.charAt(0), invoke dfs(r, c, 0):
 *     1. Base Goal: If index == word.length(), entire word is matched, return true.
 *     2. Boundary & Mismatch Checks:
 *        - If r, c is out of bounds: return false.
 *        - If board[r][c] != word.charAt(index): return false (also catches visited cells).
 *     3. Choose:
 *        - Save temp = board[r][c].
 *        - Mutate board[r][c] = '#' to mark cell as visited without allocating an extra boolean[][] matrix.
 *     4. Explore:
 *        - Recurse in 4 cardinal directions: Up (r-1, c), Down (r+1, c), Left (r, c-1), Right (r, c+1).
 *     5. Un-choose (Backtrack):
 *        - Restore board[r][c] = temp so other starting positions/paths can reuse this cell.
 * 
 * Key Invariant:
 * Marking the cell in-place with '#' avoids O(M * N) auxiliary memory allocation for a visited array
 * and achieves constant O(1) auxiliary space beyond the O(L) recursion stack.
 * 
 * Time Complexity:
 * - O(M * N * 3^L): Where M * N is grid area and L is word length. At each step of the path,
 *   we explore at most 3 directions (excluding the cell we just came from).
 * 
 * Space Complexity:
 * - O(L): Recursion call stack depth bounded by the length of the word L.
 */
public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        // Quick pruning: If word length exceeds total grid cells, impossible
        if (word.length() > m * n) {
            return false;
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }

        // Boundary check and character mismatch / visited check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // 1. Choose & mark visited in-place
        char temp = board[r][c];
        board[r][c] = '#';

        // 2. Explore 4 cardinal directions
        boolean found = dfs(board, word, r + 1, c, index + 1)
                     || dfs(board, word, r - 1, c, index + 1)
                     || dfs(board, word, r, c + 1, index + 1)
                     || dfs(board, word, r, c - 1, index + 1);

        // 3. Un-choose (Backtrack & restore cell state)
        board[r][c] = temp;

        return found;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Word Search Verification ===");

        char[][] board1 = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        // Test 1: Word "ABCCED" -> Exists
        boolean pass1 = exist(board1, "ABCCED");
        System.out.println("Test 1: Word 'ABCCED' -> " + pass1 + " (Expected true) -> " + (pass1 ? "PASS" : "FAIL"));

        // Test 2: Word "SEE" -> Exists
        boolean pass2 = exist(board1, "SEE");
        System.out.println("Test 2: Word 'SEE' -> " + pass2 + " (Expected true) -> " + (pass2 ? "PASS" : "FAIL"));

        // Test 3: Word "ABCB" -> Does NOT exist (cannot reuse cell 'B')
        boolean pass3 = !exist(board1, "ABCB");
        System.out.println("Test 3: Word 'ABCB' -> " + !pass3 + " (Expected false) -> " + (pass3 ? "PASS" : "FAIL"));

        // Test 4: Single cell board
        char[][] board2 = {{'A'}};
        boolean pass4a = exist(board2, "A");
        boolean pass4b = !exist(board2, "B");
        System.out.println("Test 4: Single cell board: 'A' -> " + pass4a + ", 'B' -> " + !pass4b + " -> " + (pass4a && pass4b ? "PASS" : "FAIL"));

        // Test 5: Verify board state was strictly restored
        boolean stateRestored = (board1[0][0] == 'A' && board1[0][1] == 'B' && board1[2][2] == 'E');
        System.out.println("Test 5: Board in-place state restoration invariant -> " + (stateRestored ? "PASS" : "FAIL"));
    }
}
