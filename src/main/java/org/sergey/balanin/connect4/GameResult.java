package org.sergey.balanin.connect4;

public enum GameResult {
    RED_WINS(Ball.RED.prompt + " wins!"),
    GREEN_WINS(Ball.GREEN.prompt + " wins!"),
    INCOMPLETE("The game has not completed"),
    DRAW("Draw!");

    private final String completionMessage;

    GameResult(String completionMessage) {
        this.completionMessage = completionMessage;
    }

    @Override
    public String toString() {
        return completionMessage;
    }
}
