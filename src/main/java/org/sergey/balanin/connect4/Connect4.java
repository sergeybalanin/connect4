package org.sergey.balanin.connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public final class Connect4 implements Game {
    static final GameOptions STANDARD_OPTIONS = new GameOptions(6, 7, 4);
    private final BufferedReader input;
    private final PrintWriter output;
    private final Board gameBoard;

    Connect4(InputStream inputStream, OutputStream outputStream, GameOptions gameOptions) {
        input = new BufferedReader(new InputStreamReader(inputStream));
        output = new PrintWriter(outputStream, true);
        gameBoard = new Board(gameOptions);
    }

    @Override
    public GameResult play() throws IOException {
        Ball currentBall = Ball.RED;
        GameResult result = GameResult.INCOMPLETE;

        while (result == GameResult.INCOMPLETE) {
            gameBoard.printTo(output);
            output.print(currentBall.prompt);
            output.print(" - choose column (1-7): ");
            output.flush();

            String userText = input.readLine();
            if (userText == null) {
                output.println();
                break;
            }

            int column = Integer.parseInt(userText);
            if (!gameBoard.canDropTo(column)) {
                output.println("This column is full, please choose another");
            } else {
                result = gameBoard.drop(currentBall, column);
                currentBall = currentBall.next();
            }
        }

        gameBoard.printTo(output);
        output.println(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        new Connect4(System.in, System.out, STANDARD_OPTIONS).play();
    }
}
