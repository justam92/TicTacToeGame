package com.example.tictactoegame.service;

import android.widget.Button;
import android.widget.Toast;

import com.example.tictactoegame.MainActivity;
import com.example.tictactoegame.model.Player;

public class GameBoardService {

    public boolean checkForWin(Button[][] buttons) {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;

    }

    public void player1Wins(Player player1, MainActivity mainActivity, Button[][] buttons, Integer roundCount, Boolean player1Turn) {
        player1.setPoints(player1.getPoints() + 1);
        updatePlayer1PointsText(player1);
        Toast.makeText(mainActivity, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        resetBoard(buttons, roundCount, player1Turn);
    }

    public void player2Wins(Player player2, MainActivity mainActivity, Button[][] buttons, Integer roundCount, Boolean player1Turn) {
        player2.setPoints(player2.getPoints() + 1);

        Toast.makeText(mainActivity, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePlayer2PointsText(player2);
        resetBoard(buttons, roundCount, player1Turn);
    }

    public void draw(MainActivity mainActivity, Button[][] buttons, Integer roundCount, Boolean player1Turn) {
        Toast.makeText(mainActivity, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard(buttons, roundCount, player1Turn);
    }

    public void resetGame(Player player1, Player player2, Button[][] buttons, Integer roundCount, Boolean player1Turn) {
        player1.setPoints(0);
        player2.setPoints(0);
        updatePointsText(player1, player2);
        resetBoard(buttons, roundCount, player1Turn);
    }

    private void updatePointsText(Player player1, Player player2) {
        updatePlayer1PointsText(player1);

        updatePlayer2PointsText(player2);
    }

    private void updatePlayer1PointsText(Player player1) {
        player1.getTextView().setText("Player 1: " + player1.getPoints());
    }

    private void updatePlayer2PointsText(Player player2) {
        player2.getTextView().setText("Player 2: " + player2.getPoints());
    }

    private void resetBoard(Button[][] buttons, Integer roundCount, Boolean player1Turn) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;

        player1Turn = true;
    }
}
