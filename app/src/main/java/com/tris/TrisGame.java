package com.tris;

import java.util.Random;

public class TrisGame {
    public static final int DIM = 3;
    public static final char HUMAN = 'X';
    public static final char PC = 'O';
    public static final char EMPTY = ' ';

    private char[][] board;
    private boolean isHumanTurn;

    public TrisGame() {
        board = new char[DIM][DIM];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = EMPTY;
            }
        }
        isHumanTurn = true;
    }

    public boolean makeMove(int row, int col) {

        if (row >= 0 && row < DIM && col >= 0 && col < DIM) {
            if (this.board[row][col] == EMPTY) {
                if (isHumanTurn) {
                    this.board[row][col] = HUMAN;
                    isHumanTurn = false;
                } else {
                    this.board[row][col] = PC;
                    isHumanTurn = true;
                }

                return true;
            }
        }
        return false;
    }

    public GameState checkGameState() {
        GameState diag1 = checkLine(board[0][0], board[1][1], board[2][2]);
        if (diag1 != null) {
            return diag1;
        }
        GameState diag2 = checkLine(board[0][2], board[1][1], board[2][0]);
        if (diag2 != null) {
            return diag2;
        }
        for (int i = 0; i < DIM; i++) {
            GameState rowResult = checkLine(board[i][0], board[i][1], board[i][2]);
            if (rowResult != null)
                return rowResult;

            GameState colResult = checkLine(board[0][i], board[1][i], board[2][i]);
            if (colResult != null)
                return colResult;
        }
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (this.board[i][j] == EMPTY) {
                    return GameState.IN_PROGRESS;
                }
            }
        }
        return GameState.DRAW;

    }

    private GameState checkLine(char c1, char c2, char c3) {
        if (c1 != EMPTY && c1 == c2 && c2 == c3) {
            if (c1 == HUMAN) {
                return GameState.HUMAN_WIN;
            } else {
                return GameState.COMPUTER_WIN;
            }
        }
        return null;
    }

    public void MakeComputerMove() {
        int tried = 0;
        while (true) {
            Random rand = new Random();
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            if (this.board[row][col] == EMPTY) {
                this.board[row][col] = PC;
                break;
            } else {
                tried++;
            }
            if (tried > 9) {
                break;
            }
        }
        isHumanTurn = true;
    }

    public void printBoard() {
        for (int i = 0; i < DIM; i++) {
            System.out.print("| ");
            for (int j = 0; j < DIM; j++) {
                System.out.print(this.board[i][j] + " | ");
                if (j == 2) {
                    System.out.println("");

                }
            }
        }
    }

    public void printResult(GameState state) {
        if (state == GameState.HUMAN_WIN) {
            System.out.println("YOU WIN");
        } else if (state == GameState.COMPUTER_WIN) {
            System.out.println("PC WIN");
        } else {
            System.out.println("It's a draw, try again");
        }
    }
}
