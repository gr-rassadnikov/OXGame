package com.example.oxgame.game;

public interface Position {
    Cell getTurn();

    boolean isValid(TicTacMove move);
    Cell getCell(int row, int column);
}
