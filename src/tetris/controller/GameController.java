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
            updateNext();

            while (board.tryToMoveDown()) {
                Thread.sleep(50);

                updateBoard();
            }
        }
        frame.gameOverAlert();
    }

    private void updateBoard() {
        for (int i = 0; i < board.getRowsCount(); i++) {
            for (int j = 0; j < board.getColumnsCount(); j++) {
                Color color = board.getColorOf(i, j);
                frame.updateBoardField(i, j, color);
            }
        }
    }
    
    private void updateNext() {
        frame.updateNextField(board.getNextBrick(), board.getNextColor());
    }

}
