package tetris.controller;

import java.awt.Color;
import java.util.Random;
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

            destroyLines();
            generateNewBrick();
            
            while (board.tryToMoveDown()) {
                Thread.sleep(500);
                updateBoard();
            }
        }
        frame.gameOverAlert();
    }

    private void destroyLines() {
        int destroyedLines = board.tryToDestroyLines();
        frame.addScore(destroyedLines * 1000);
        frame.addDestroyedLines(destroyedLines);
    }
    
    private void generateNewBrick() {
        board.generateNewBrick();
        frame.addScore(50);
        frame.updateNextField(board.getNextBrick(), board.getNextColor());
    }

    private void updateBoard() {
        for (int i = 0; i < board.getRowsCount(); i++) {
            for (int j = 0; j < board.getColumnsCount(); j++) {
                Color color = board.getColorOf(i, j);
                frame.updateBoardField(i, j, color);
            }
        }
    }

}
