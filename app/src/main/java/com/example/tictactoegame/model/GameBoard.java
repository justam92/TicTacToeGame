package com.example.tictactoegame.model;

import java.util.Objects;

public class GameBoard {

    private Boolean player1Turn = true;

    private Integer roundCount = 0;

    public Boolean getPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(Boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    public Integer getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(Integer roundCount) {
        this.roundCount = roundCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameBoard that = (GameBoard) o;
        return player1Turn.equals(that.player1Turn) &&
                roundCount.equals(that.roundCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1Turn, roundCount);
    }

    @Override
    public String toString() {
        return "GameBoard{" +
                "player1Turn=" + player1Turn +
                ", roundCount=" + roundCount +
                '}';
    }
}
