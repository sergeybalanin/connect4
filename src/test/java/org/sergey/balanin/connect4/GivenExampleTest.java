package org.sergey.balanin.connect4;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class GivenExampleTest {

    @Test
    public void scenario() {
        InputStream userInput = createStdin(new int[]{4, 4, 5, 5, 3, 2, 6});
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

        Game game = runGame(userInput, consoleOutput);
        GameResult result = game.play();

        assertEquals(GameResult.RED_WINS, result);
    }

    private static InputStream createStdin(int[] playerMoves) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        for (int playerMove : playerMoves) {
            writer.print(playerMove);
            writer.println();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static Game runGame(InputStream input, OutputStream output) {
        throw new RuntimeException("Not implemented");
    }
}
