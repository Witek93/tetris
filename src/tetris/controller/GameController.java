package tetris.controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.model.GameBoard;
import tetris.model.Score;
import tetris.view.GameFrame;

public class GameController {

    private final GameBoard board;
    private final GameFrame frame;
    private final Score score;

    public GameController(GameBoard board, GameFrame frame) {
        this.board = board;
        this.frame = frame;
        this.score = new Score();
        frame.setKeyListener(new ArrowKeysListener());
    }

    public void start() throws InterruptedException {
        while (!board.isGameOver()) {

            destroyLines();
            generateNewBrick();
            updateBoardView();

            while (board.tryToMoveDown()) {
                updateTime();
                updateBoardView();
            }
            
            board.mergeBrick();
            
        }
        frame.gameOverAlert();
    }
    
    private void updateTime() throws InterruptedException{
        int destroyedLines = Integer.parseInt(score.getLinesValue());
        if(destroyedLines<=5 )
            Thread.sleep(1000);
        else if(destroyedLines>5 && destroyedLines<=10 )
            Thread.sleep(900);
        else if(destroyedLines>10 && destroyedLines<=15 )
            Thread.sleep(800);
        else if(destroyedLines>15 && destroyedLines<=20 )
            Thread.sleep(700);
        else if(destroyedLines>20 && destroyedLines<=25 )
            Thread.sleep(600);
        else if(destroyedLines>25 && destroyedLines<=30 )
            Thread.sleep(500);
        else
            Thread.sleep(300);
    }
    
    private void destroyLines() {
        while(board.tryToDestroyLine()) {
            score.incrementLines();
            frame.setDestroyedLines(score.getLinesValue());
            score.addPoints(1000);
            frame.setScore(score.getPointsValue());
        }
    }

    private void generateNewBrick() {
        board.nextBrick();
        score.addPoints(50);
        frame.setScore(score.getPointsValue());
        frame.updateNextFieldPanel(board.getNextBrick(), board.getNextColor());
    }

    private void updateBoardView() {
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
                    board.tryToRotate();
                    break;
                case KeyEvent.VK_DOWN:
                    board.tryToMoveDown();
                    break;
                case KeyEvent.VK_LEFT:
                    board.tryToMoveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    board.tryToMoveRight();
                    break;
            }
            updateBoardView();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

}
