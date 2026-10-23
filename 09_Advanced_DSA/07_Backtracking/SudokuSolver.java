package advanced.backtracking;

/**
 * Problem: Sudoku Solver (LeetCode 37).
 * Difficulty: Hard.
 * Pattern: Constraint Satisfaction Backtracking with In-Place State Mutation.
 * 
 * Problem Statement:
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 * 1. Each of the digits 1-9 must occur exactly once in each row.
 * 2. Each of the digits 1-9 must occur exactly once in each column.
 * 3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * 
 * Approach (Constraint Propagation DFS):
 * - Search the board for the next empty cell (denoted by '.').
 * - Once an empty cell (r, c) is found, attempt placing digits '1' through '9':
 *     1. Validate constraints in O(1):
 *        - Row constraint: Digit cannot already exist in row r.
 *        - Column constraint: Digit cannot already exist in column c.
 *        - Box constraint: Digit cannot already exist in the 3x3 sub-box starting at
 *          row (r / 3) * 3 and col (c / 3) * 3.
 *     2. Choose:
 *        - Mutate board[r][c] = d.
 *     3. Explore:
 *        - Recursively attempt to solve remaining empty cells: if (solve(board)) return true.
 *     4. Un-choose (Backtrack):
 *        - If no downstream assignment leads to a valid solution, reset board[r][c] = '.'
 *          and try the next candidate digit.
 * - If all digits 1-9 fail for cell (r, c), return false to prune this branch.
 * - If all 81 cells are successfully filled without conflict, return true (puzzle solved).
 * 
 * Key Invariants:
 * - Returning boolean allows immediate short-circuiting once the first valid complete configuration is found,
 *   avoiding redundant exploration of already-solved decision trees.
 * - Mutating the board in-place guarantees O(1) auxiliary space beyond the recursion call stack.
 * 
 * Time Complexity:
 * - O(9^(E)): Where E is the number of empty cells (at most 81). In practice, Sudoku constraint
 *   pruning heavily cuts down the branching factor, terminating in milliseconds.
 * 
 * Space Complexity:
 * - O(E) = O(81) = O(1): Recursion stack depth is strictly bounded by the number of empty cells.
 */
public class SudokuSolver {

    public static void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }
        solve(board);
    }

    private static boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    for (char d = '1'; d <= '9'; d++) {
                        if (isValid(board, r, c, d)) {
                            // 1. Choose
                            board[r][c] = d;

                            // 2. Explore
                            if (solve(board)) {
                                return true;
                            }

                            // 3. Un-choose (Backtrack)
                            board[r][c] = '.';
                        }
                    }
                    // No digit 1-9 is valid in this empty cell; must backtrack
                    return false;
                }
            }
        }
        // All cells filled validly
        return true;
    }

    private static boolean isValid(char[][] board, int r, int c, char d) {
        int boxRowStart = 3 * (r / 3);
        int boxColStart = 3 * (c / 3);

        for (int i = 0; i < 9; i++) {
            // Check row r
            if (board[r][i] == d) return false;
            // Check column c
            if (board[i][c] == d) return false;
            // Check 3x3 sub-box
            if (board[boxRowStart + i / 3][boxColStart + i % 3] == d) return false;
        }
        return true;
    }

    /**
     * Verifies whether a completed 9x9 board satisfies all Sudoku row, column, and box invariants.
     */
    public static boolean isBoardValid(char[][] board) {
        for (int r = 0; r < 9; r++) {
            boolean[] rowSeen = new boolean[10];
            boolean[] colSeen = new boolean[10];
            for (int c = 0; c < 9; c++) {
                char rowVal = board[r][c];
                if (rowVal < '1' || rowVal > '9' || rowSeen[rowVal - '0']) return false;
                rowSeen[rowVal - '0'] = true;

                char colVal = board[c][r];
                if (colVal < '1' || colVal > '9' || colSeen[colVal - '0']) return false;
                colSeen[colVal - '0'] = true;
            }
        }

        for (int block = 0; block < 9; block++) {
            boolean[] boxSeen = new boolean[10];
            int startR = (block / 3) * 3;
            int startC = (block % 3) * 3;
            for (int i = 0; i < 9; i++) {
                char val = board[startR + i / 3][startC + i % 3];
                if (val < '1' || val > '9' || boxSeen[val - '0']) return false;
                boxSeen[val - '0'] = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== 09_Advanced_DSA: Sudoku Solver Verification ===");

        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println("Input Puzzle:");
        printBoard(board);

        solveSudoku(board);

        System.out.println("\nSolved Board:");
        printBoard(board);

        // Verification tests
        boolean valid = isBoardValid(board);
        System.out.println("\nTest 1: Solved board satisfies all row/col/box invariants -> " + (valid ? "PASS" : "FAIL"));

        // Verify key cells match expected values
        boolean cellsMatch = board[0][0] == '5' && board[0][2] == '4' && board[8][8] == '9';
        System.out.println("Test 2: Cell values match canonical solution -> " + (cellsMatch ? "PASS" : "FAIL"));
    }

    private static void printBoard(char[][] board) {
        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0 && r != 0) {
                System.out.println("------+-------+------");
            }
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0 && c != 0) {
                    sb.append("| ");
                }
                sb.append(board[r][c]).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
