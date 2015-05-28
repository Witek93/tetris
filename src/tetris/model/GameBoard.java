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
            if (defaultLayer.overlapsWith(activeBrick)) {
                activeBrick.moveUp();
                mergeBrick();
                return false;
            }
            return true;
        } else {
            mergeBrick();
            return false;
        }

//        mergeBrick();
//        if (activeLayer.isOnBottom()) {
//            mergeLayers();
//            return false;
//        } else {
//            Layer layer = activeLayer.getMovedDown();
//
//            if (layer.overlapsWith(defaultLayer)) {
//                mergeLayers();
//                return false;
//            } else {
//                activeLayer = layer;
//                return true;
//            }
//        }
    }

    public void tryToMoveRight() {
        if (activeBrick.getColumnShift() + activeBrick.getWidth() < getColumnsCount()) {
            activeBrick.moveRight();
        }
    }

    public void tryToMoveLeft() {
        if (activeBrick.getColumnShift() > 0) {
            activeBrick.moveLeft();
        }
    }

    public void rotateBrick() {
        activeBrick.rotate();
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

    public void generateNewBrick() {
        activeBrick.setBrick(nextBrick, getColumnsCount());
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
//        return activeBrick.getRowShift() == 0;
        return false; //TODO
    }

}
