package com.example.oxgame.game;

public interface Board {
    Position getPosition();
    GameResult makeMove(TicTacMove move);

    Cell[][] getField();

    int getN();
    int getM();
}
