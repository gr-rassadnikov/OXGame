package com.example.oxgame.game.MNKPlayers;

import java.util.Random;
import java.util.Scanner;

import com.example.oxgame.game.*;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    final int n, m;
    public RandomPlayer(TicTacToeBoard board) {
        this.m = board.getM();
        this.n = board.getN();
    }

    @Override
    public TicTacMove makeMove(Position position, Cell[][] field, int i, int j) {
        while (true) {
            final TicTacMove move = new TicTacMove(
                    random.nextInt(m),
                    random.nextInt(n),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }


    @Override
    public void setRival(Player rival) {

    }

    @Override
    public void setN(int n) {

    }

    @Override
    public void setM(int m) {

    }

    @Override
    public int getN() {
        return 0;
    }

    @Override
    public int getM() {
        return 0;
    }
}
