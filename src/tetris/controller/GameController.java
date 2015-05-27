package tetris.controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.model.GameBoard;
import tetris.view.GameFrame;

public class GameController {

    private final GameBoard board;
    private final GameFrame frame;

    public GameController(GameBoard board, GameFrame frame) {
        this.board = board;
        this.frame = frame;
        frame.setKeyListener(new ArrowKeysListener());
    }

    public void start() throws InterruptedException {
        while (!board.isGameOver()) {

            destroyLines();
            generateNewBrick();

            while (board.moveDown()) {
                Thread.sleep(100);
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

    private class ArrowKeysListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    System.out.println("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    board.moveDown();
                    break;
                case KeyEvent.VK_LEFT:
                    board.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    board.tryToMoveRight();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

}
