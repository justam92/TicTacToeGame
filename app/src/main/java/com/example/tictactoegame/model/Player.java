package com.example.tictactoegame.model;

import android.widget.TextView;

import java.util.Objects;

public class Player {

    private int points;

    private TextView textView;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return points == player.points &&
                Objects.equals(textView, player.textView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, textView);
    }

    @Override
    public String toString() {
        return "Player{" +
                "points=" + points +
                ", textView=" + textView +
                '}';
    }
}
