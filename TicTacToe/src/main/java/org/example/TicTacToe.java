package org.example;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class TicTacToe {
    private static final char EMPTY = '-';
    private static int SIZE;
    private char[][] board;
    private char currentPlayer;
    private Map<Integer, List<Integer>> pos;

    public TicTacToe() {
        SIZE = 3;
        board = new char[SIZE][SIZE];
        currentPlayer = 'X';
        initializeBoard(SIZE);
        pos = CommonUtils.getIndexsMap(SIZE);
    }

    public TicTacToe(int SIZE) {
        this.SIZE = SIZE;
        board = new char[SIZE][SIZE];
        currentPlayer = 'X';
        initializeBoard(SIZE);
        pos = CommonUtils.getIndexsMap(SIZE);
    }

    // Initialize the board with empty cells
    private void initializeBoard(int SIZE) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Print the board
    private void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if a player has won
    private boolean hasPlayerWon(char player) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            boolean rowWon = true;
            boolean colWon = true;
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != player) {
                    rowWon = false;
                }
                if (board[j][i] != player) {
                    colWon = false;
                }
            }
            if (rowWon || colWon) {
                return true;
            }
        }
        boolean diagonalWon = true;
        boolean rDiagonalWon = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != player) diagonalWon = false;
            if (board[i][SIZE - i - 1] != player) rDiagonalWon = false;
        }
        return diagonalWon || rDiagonalWon;
    }

    // Make a move
    private boolean makeMove(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || board[row][col] != EMPTY) {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    // Switch to the next player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Play the game
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        printBoard();
        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move ");
            int n = scanner.nextInt();
            int row = pos.get(n).get(0);
            int col = pos.get(n).get(1);
            System.out.println(row + "," + col);

            if (makeMove(row, col)) {
                printBoard();

                if (hasPlayerWon(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }

                switchPlayer();
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }

        scanner.close();
    }
}
