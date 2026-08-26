package com.tris;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TrisGame newGame = new TrisGame();
        System.out.println("Tris - Single Player - Game Start");
        newGame.printBoard();
        GameState currentGame = newGame.checkGameState();

        while (currentGame == GameState.IN_PROGRESS) {
            System.out.println("Chose a row and a col: ");
            boolean validMove = false;

            while (!validMove) {
                System.out.print("Row: ");
                int row1 = Integer.valueOf(scan.nextLine());
                System.out.print("Col: ");
                int col1 = Integer.valueOf(scan.nextLine());
                validMove = newGame.makeMove(row1, col1);
                if (!validMove) {
                    System.out.println("Mossa non valida, ritenta");
                }
            }
            currentGame = newGame.checkGameState();
            if (currentGame != GameState.IN_PROGRESS) {
                newGame.printBoard();
                break;
            }
            System.out.println("Turno PC");
            newGame.MakeComputerMove();
            newGame.printBoard();
            currentGame = newGame.checkGameState();
        }
        newGame.printResult(currentGame);
        scan.close();

    }
}
