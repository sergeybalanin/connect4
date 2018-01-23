package org.sergey.balanin.connect4;

public final class GameOptions {
    final int rows;
    final int columns;
    final int winLength;

    public GameOptions(int rows, int columns, int winLength) {
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
    }
}
