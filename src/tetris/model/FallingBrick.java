package tetris.model;

import java.awt.Color;
import tetris.model.bricks.Brick;

public class FallingBrick {

    private int rowShift, columnShift;
    private Brick brick;

    public FallingBrick() {
        this.rowShift = 0;
        this.columnShift = 0;
        this.brick = null;
    }

    public void setBrick(Brick brick, int columnShift) {
        this.brick = brick;
        this.columnShift = columnShift;
        this.rowShift = 0;
    }
    
    public void rotate(int columnsCount) {
        brick.rotate();
        if(isBoundariesPassed(columnsCount)) {
            brick.rotateBack();
        }
    }
        
    private boolean isBoundariesPassed(int columnsCount) {
        return (getColumnShift() < 0) || (getColumnShift() + getWidth() > columnsCount);
    }

    public void rotateBack() {
        brick.rotateBack();
    }

    public int getRowShift() {
        return rowShift;
    }

    public int getColumnShift() {
        return columnShift;
    }

    public void moveDown() {
        rowShift++;
    }

    public void moveUp() {
        rowShift--;
    }

    public void moveRight() {
        columnShift++;
    }

    public void moveLeft() {
        columnShift--;
    }

    public boolean isOnBottom(int rowsCount) {
        return rowsCount == this.rowShift + brick.getHeight();
    }

    public boolean isOccupied(int row, int column) {
        return getColor(row, column) != Brick.DEFAULT_COLOR;
    }
    
    public boolean canMoveRight(int columnsCount) {
        return getColumnShift() + getWidth() < columnsCount;
    }
    
    public boolean canMoveLeft() {
        return getColumnShift() > 0;
    }


    public Color getColor(int row, int column) {
        return brick.getColor(row - rowShift, column - columnShift);
    }

    public int getWidth() {
        return brick.getWidth();
    }

    public int getHeight() {
        return brick.getHeight();
    }

}
