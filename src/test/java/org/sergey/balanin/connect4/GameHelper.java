package org.sergey.balanin.connect4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class GameHelper {
    private final GameOptions gameOptions;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    GameHelper(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    final Game prepareGame(int[] playerMoves) {
        return new Connect4(createStdin(playerMoves), output, gameOptions);
    }

    final String consoleOutput() throws UnsupportedEncodingException {
        return output.toString(Charset.defaultCharset().name());
    }

    private InputStream createStdin(int[] playerMoves) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        for (int playerMove : playerMoves) {
            writer.print(playerMove);
            writer.println();
        }
        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
