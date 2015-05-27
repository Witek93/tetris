package tetris.model;

import java.awt.Color;
import tetris.model.bricks.Brick;

public class GameBoard {

    private final Layer defaultLayer;
    private Layer activeLayer;
    private Brick nextBrick;

    public GameBoard(int rowCount, int columnCount) {
        this.defaultLayer = new Layer(rowCount, columnCount);
        this.activeLayer = new Layer(rowCount, columnCount);
        this.nextBrick = BrickFactory.create();
    }

    public int getRowsCount() {
        return defaultLayer.getRowsCount();
    }

    public int getColumnsCount() {
        return defaultLayer.getColumnsCount();
    }

    public boolean moveDown() {
        if (activeLayer.isOnBottom()) {
            mergeLayers();
            return false;
        } else {
            Layer layer = activeLayer.getMovedDown();

            if (layer.overlapsWith(defaultLayer)) {
                mergeLayers();
                return false;
            } else {
                activeLayer = layer;
                return true;
            }
        }
    }

    public void tryToMoveRight() {
        Layer moved = activeLayer.getMovedRight();
        if (!defaultLayer.overlapsWith(moved)) {
            activeLayer = moved;
        }
    }

    public void moveLeft() {
        Layer moved = activeLayer.getMovedLeft();
        if (!defaultLayer.overlapsWith(moved)) {
            activeLayer = moved;
        }
    }

    public int tryToDestroyLines() {
        int destroyedLines = 0;
        while (defaultLayer.tryToDestroyLine()) {
            destroyedLines++;
        }
        return destroyedLines;
    }

    private void mergeLayers() {
        defaultLayer.append(activeLayer);
    }

    public void generateNewBrick() {
        activeLayer.reset();
        activeLayer.put(nextBrick.getVariant(), nextBrick.getColor());

        //generujemy nowy bloczek po dodaniu poprzedniego, aby móc wyświetlić
        //go w panelu "Next"
        nextBrick = BrickFactory.create();
    }

    public boolean[][] getNextBrick() {
        return nextBrick.getVariant();
    }

    public Color getNextColor() {
        return nextBrick.getColor();
    }

    public Color getColorOf(int row, int column) {
        if (defaultLayer.isFullField(row, column)) {
            return defaultLayer.getField(row, column);
        } else {
            return activeLayer.getField(row, column);
        }
    }

    public boolean isGameOver() {
        return activeLayer.isOnTop();
    }

}
