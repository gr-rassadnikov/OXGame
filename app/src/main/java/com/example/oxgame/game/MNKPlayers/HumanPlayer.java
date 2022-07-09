package com.example.oxgame.game.MNKPlayers;

import java.util.Scanner;

import com.example.oxgame.game.*;

public class HumanPlayer implements Player {
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    int n;

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    int m;

    Player rival;

    public void setRival(Player rival) {
        this.rival = rival;
    }

    public HumanPlayer() {
    }

    @Override
    public TicTacMove makeMove(Position position, Cell[][] field, int i, int j) {
        return new TicTacMove(i, j, position.getTurn());
    }

    boolean checkCorrect(int moveRow, int moveCol, Cell[][] field) {
        if (moveCol < 0 || moveRow < 0 || moveRow >= m || moveCol >= n || field[moveRow][moveCol] != Cell.E) {
            return false;
        }
        return true;
    }

}

