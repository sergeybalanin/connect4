package org.sergey.balanin.connect4;

import java.io.IOException;

public interface Game {

    GameResult play() throws IOException;
}
