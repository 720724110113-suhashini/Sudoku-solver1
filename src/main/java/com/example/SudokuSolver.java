package com.example.sudoku;

public class SudokuSolver {

    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Find an empty cell
                if (board[row][col] == '.') {

                    // Try numbers 1 to 9
                    for (char num = '1'; num <= '9'; num++) {

                        if (isValid(board, row, col, num)) {

                            // Place number
                            board[row][col] = num;

                            // Recursively solve the remaining board
                            if (solve(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = '.';
                        }
                    }

                    // No number works for this cell
                    return false;
                }
            }
        }

        // No empty cells remain
        return true;
    }

    private static boolean isValid(
            char[][] board,
            int row,
            int col,
            char num) {

        for (int i = 0; i < 9; i++) {

            // Check row
            if (board[row][i] == num) {
                return false;
            }

            // Check column
            if (board[i][col] == num) {
                return false;
            }

            // Check 3x3 box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;

            if (board[boxRow][boxCol] == num) {
                return false;
            }
        }

        return true;
    }

    private static void printBoard(char[][] board) {

        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

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

        System.out.println("Original Sudoku:");
        printBoard(board);

        solveSudoku(board);

        System.out.println("\nSolved Sudoku:");
        printBoard(board);
    }
}