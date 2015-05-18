package tetris.controller;

import java.awt.Color;
import tetris.model.GameBoard;
import tetris.view.GameFrame;

public class GameController {

    private final GameBoard board;
    private final GameFrame frame;

    public GameController(GameBoard board, GameFrame frame) {
        this.board = board;
        this.frame = frame;
    }

    public void start() throws InterruptedException {
        while (!board.isGameOver()) {

            board.generateNewBrick();

            while (board.tryToMoveDown()) {
                Thread.sleep(50);

                for (int i = 0; i < board.getRowsCount(); i++) {
                    for (int j = 0; j < board.getColumnsCount(); j++) {
                        Color color = board.getColorOf(i, j);
                        frame.updateField(i, j, color);
                    }
                }
            }
        }
        frame.gameOverAlert();
    }

}
