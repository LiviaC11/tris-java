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

        if (row > 0 && row < DIM && col > 0 && col < DIM) {
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
        if (board[0][0] == HUMAN && board[1][1] == HUMAN && board[2][2] == HUMAN) {
            return GameState.HUMAN_WIN;
        }
        if (board[0][0] == PC && board[1][1] == PC && board[2][2] == PC) {
            return GameState.COMPUTER_WIN;
        }
        if (board[0][2] == PC && board[1][1] == PC && board[2][0] == PC) {
            return GameState.COMPUTER_WIN;
        }
        if (board[0][2] == HUMAN && board[1][1] == HUMAN && board[2][0] == HUMAN) {
            return GameState.HUMAN_WIN;
        }
        for (int i = 0; i < DIM; i++) {
            int rowX = 0, rowO = 0;
            int colX = 0, colO = 0;

            for (int j = 0; j < DIM; j++) {
                // Controllo della RIGA i
                if (this.board[i][j] == HUMAN)
                    rowX++;
                else if (this.board[i][j] == PC)
                    rowO++;

                // Controllo della COLONNA i
                if (this.board[j][i] == HUMAN)
                    colX++;
                else if (this.board[j][i] == PC)
                    colO++;
            }

            // Verifica vittoria per la riga i o la colonna i
            if (rowX == 3 || colX == 3)
                return GameState.HUMAN_WIN;
            if (rowO == 3 || colO == 3)
                return GameState.COMPUTER_WIN;
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

    }
}
