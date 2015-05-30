package tetris.model;

import java.awt.Color;
import tetris.model.bricks.Brick;

public class GameBoard {

    private final Layer defaultLayer;
    private final FallingBrick activeBrick;
    private Brick nextBrick;

    public GameBoard(int rowCount, int columnCount) {
        this.defaultLayer = new Layer(rowCount, columnCount);
        this.nextBrick = BrickFactory.create();
        this.activeBrick = new FallingBrick();
    }

    public int getRowsCount() {
        return defaultLayer.getRowsCount();
    }

    public int getColumnsCount() {
        return defaultLayer.getColumnsCount();
    }

    public boolean moveDown() {
        if (!activeBrick.isOnBottom(getRowsCount())) {
            activeBrick.moveDown();
            if (!isValidState()) {
                activeBrick.moveUp();
                mergeBrick();
                return false;
            }
            return true;
        } else {
            mergeBrick();
            return false;
        }

    }

    public void tryToMoveRight() {
        if (activeBrick.canMoveRight(getColumnsCount())) {
            activeBrick.moveRight();
            if (!isValidState()) {
                activeBrick.moveLeft();
            }
        }
    }

    public void tryToMoveLeft() {
        if (activeBrick.canMoveLeft()) {
            activeBrick.moveLeft();
            if (!isValidState()) {
                activeBrick.moveRight();
            }
        }
    }

    public void rotateBrick() {
        activeBrick.rotate(getColumnsCount());
        if (!isValidState()) {
            activeBrick.rotateBack();
        }
    }

    public boolean tryToDestroyLine() {
        return defaultLayer.tryToDestroyLine();
    }
    
    public int tryToDestroyLines() {
        int destroyedLines = 0;
        while (defaultLayer.tryToDestroyLine()) {
            destroyedLines++;
        }
        return destroyedLines;
    }

    private void mergeBrick() {
        defaultLayer.append(activeBrick);
    }

    public void nextBrick() {
        int columnShift = (getColumnsCount() - nextBrick.getWidth()) / 2;
        activeBrick.setBrick(nextBrick, columnShift);
        nextBrick = BrickFactory.create();
    }

    public boolean[][] getNextBrick() {
        return nextBrick.getVariant();
    }

    public Color getNextColor() {
        return nextBrick.getColor();
    }

    public Color getColorOf(int row, int column) {
        if (defaultLayer.isOccupied(row, column)) {
            return defaultLayer.getField(row, column);
        } else {
            return activeBrick.getColor(row, column);
        }
    }

    public boolean isGameOver() {
        return activeBrick.getRowShift() == 0 && !isValidState();
    }

    private boolean isValidState() {
        return !defaultLayer.overlapsWith(activeBrick);
    }

}
