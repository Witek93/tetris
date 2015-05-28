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
    
    public void setBrick(Brick brick, int columnsCount) {
        this.brick = brick;
        this.columnShift = (columnsCount - brick.getVariant()[0].length) / 2;
        this.rowShift = 0;
    }
    
    public void rotate() {
        brick.rotate();
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
    
    public Color getColor(int row, int column) {
        return brick.getColor(row - rowShift, column - columnShift);
    }
    
    public boolean isOccupied(int row, int column) {
        return getColor(row, column) != Brick.DEFAULT_COLOR;
    }
    
    public int getWidth() {
        return brick.getWidth();
    }
    
    public int getHeight() {
        return brick.getHeight();
    }
    
}
