package org.sergey.balanin.connect4;

public enum Ball {
    RED('R', "Player 1 [RED]") {
        @Override
        GameResult playerWins() {
            return GameResult.RED_WINS;
        }
    },
    GREEN('G', "Player 2 [GREEN]") {
        @Override
        GameResult playerWins() {
            return GameResult.GREEN_WINS;
        }
    };

    final char symbol;
    final String prompt;

    Ball(char symbol, String prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
    }

    abstract GameResult playerWins();

    Ball next() {
        return Ball.values()[(ordinal() + 1) % Ball.values().length];
    }
}
