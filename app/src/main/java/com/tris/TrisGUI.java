package com.tris;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class TrisGUI extends JFrame {
    private TrisGame game;
    private JButton[][] button;

    public TrisGUI() {
        game = new TrisGame();
        button = new JButton[TrisGame.DIM][TrisGame.DIM];
        setTitle("Tris - Single Player");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(TrisGame.DIM, TrisGame.DIM));
        for (int i = 0; i < TrisGame.DIM; i++) {
            for (int j = 0; j < TrisGame.DIM; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Aerial", Font.BOLD, 40));
                btn.setFocusable(false);

                final int row = i;
                final int col = j;
                btn.addActionListener(e -> handleMove(row, col));
                button[i][j] = btn;
                add(boardPanel, BorderLayout.CENTER);
            }
        }
    }

    private boolean handleMove(int row, int col) {
        boolean success = this.game.makeMove(row, col);
        if (success) {
            button[row][col].setText("X");
        }
        GameState stato = game.checkGameState();
        if (stato != GameState.IN_PROGRESS) {
            game.printResult(stato);
            return true;
        }
        game.MakeComputerMove();

        return success;
    }

}
