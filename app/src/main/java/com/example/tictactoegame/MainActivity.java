package com.example.tictactoegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoegame.model.GameBoard;
import com.example.tictactoegame.model.Player;
import com.example.tictactoegame.service.GameBoardService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GameBoardService gameBoardService = new GameBoardService();

    private Button[][] buttons = new Button[3][3];

    private Player player1 = new Player();
    private Player player2 = new Player();

    private GameBoard gameBoard = new GameBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1.setTextView((TextView) findViewById(R.id.text_view_p1));
        player2.setTextView((TextView) findViewById(R.id.text_view_p2));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoardService.resetGame(player1, player2, buttons, gameBoard);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (gameBoard.getPlayer1Turn()){
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        gameBoard.setRoundCount(gameBoard.getRoundCount() + 1);

        if (gameBoardService.checkForWin(buttons)) {
            if (gameBoard.getPlayer1Turn()) {
                gameBoardService.player1Wins(player1, this, buttons, gameBoard);
            } else {
                gameBoardService.player2Wins(player2, this, buttons, gameBoard);
            }
        } else if (gameBoard.getRoundCount().equals(9)) {
            gameBoardService.draw(this, buttons, gameBoard);
        } else {
            gameBoard.setPlayer1Turn(!gameBoard.getPlayer1Turn());
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", gameBoard.getRoundCount());
        outState.putInt("player1Points", player1.getPoints());
        outState.putInt("player2Points", player2.getPoints());
        outState.putBoolean("player1Turn", gameBoard.getPlayer1Turn());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameBoard.setRoundCount(savedInstanceState.getInt("roundCount"));
        player1.setPoints(savedInstanceState.getInt("player1Points"));
        player2.setPoints(savedInstanceState.getInt("player2Points"));
        gameBoard.setPlayer1Turn(savedInstanceState.getBoolean("player1Turn"));
    }
}
