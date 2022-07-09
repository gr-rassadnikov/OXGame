package com.example.oxgame.game.MNKPlayers;

import com.example.oxgame.game.*;

public interface Player {
    TicTacMove makeMove(Position position, Cell[][] field, int i, int j);

    void setRival(Player rival);

    void setN(int n);

    void setM(int m);

    int getN();

    int getM();
}
