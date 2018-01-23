package org.sergey.balanin.connect4;

import java.io.PrintWriter;

final class Board {
    private final GameOptions gameOptions;
    private final Ball[][] columns;
    private final int[] insertPositions;
    private int ballsTillDraw;

    Board(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
        columns = new Ball[gameOptions.columns][];
        for (int i = 0; i < gameOptions.columns; i++) {
            columns[i] = new Ball[gameOptions.rows];
        }
        insertPositions = new int[gameOptions.columns];
        ballsTillDraw = gameOptions.rows * gameOptions.columns;
    }

    void printTo(PrintWriter writer) {
        for (int row = gameOptions.rows - 1; row >= 0; row--) {
            for (Ball[] column : columns) {
                Ball ball = column[row];
                writer.print('|');
                writer.print(ball != null ? ball.symbol : ' ');
            }
            writer.println('|');
        }
        writer.println();
    }

    boolean canDropTo(int column) {
        return insertPositions[column] < gameOptions.rows;
    }

    GameResult drop(Ball ball, int column) {
        int row = insertPositions[column];
        columns[column][row] = ball;
        insertPositions[column] = row + 1;
        GameResult gameResult = checkWinner(ball, row, column);
        ballsTillDraw -= 1;
        if (gameResult == GameResult.INCOMPLETE && ballsTillDraw == 0) {
            gameResult = GameResult.DRAW;
        }
        return gameResult;
    }

    private GameResult checkWinner(Ball ball, int row, int column) {
        for (Directions directions : Directions.values()) {
            int count = 1
                    + ballsInRow(ball, row, column, directions.shift)
                    + ballsInRow(ball, row, column, directions.shift.reverse());
            if (count >= gameOptions.winLength) {
                return ball.playerWins();
            }
        }
        return GameResult.INCOMPLETE;
    }

    private int ballsInRow(Ball ball, int row, int column, Shift shift) {
        int cRow = row + shift.rowShift;
        int cColumn = column + shift.columnShift;
        int count = 0;
        while (cRow >= 0 && cRow < gameOptions.rows && cColumn >= 0 && cColumn < gameOptions.columns && columns[cColumn][cRow] == ball) {
            count++;
            cRow += shift.rowShift;
            cColumn += shift.columnShift;
        }
        return count;
    }

    private static final class Shift {
        private final int rowShift;
        private final int columnShift;

        private Shift(int rowShift, int columnShift) {
            this.rowShift = rowShift;
            this.columnShift = columnShift;
        }

        private Shift reverse() {
            return new Shift(-rowShift, -columnShift);
        }
    }

    private enum Directions {
        RIGHT(0, 1),
        RIGHT_UP(1, 1),
        UP(1, 0),
        LEFT_UP(-1, 1);

        private final Shift shift;

        Directions(int rowShift, int columnShift) {
            this.shift = new Shift(rowShift, columnShift);
        }
    }
}
