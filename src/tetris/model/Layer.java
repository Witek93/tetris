package tetris.model;

import java.awt.Color;
import tetris.model.bricks.Brick;

public class Layer {

    private final Color[][] fields;
    private Brick brick;

    public Layer(int rowsCount, int columnsCount) {
        this.fields = new Color[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                this.fields[i][j] = Brick.DEFAULT_COLOR;
            }
        }
        this.brick = null;
    }

    private Layer(int rowsCount, int columnsCount, Brick brick) {
        this(rowsCount, columnsCount);
        this.brick = brick;
    }

    public void setBrick(Brick brick) {
        this.brick = brick;
        this.put(brick.getVariant(), brick.getColor());
    }

    public int getRowsCount() {
        return this.fields.length;
    }

    public int getColumnsCount() {
        return this.fields[0].length;
    }

    public Color getField(int row, int column) {
        if (isValidRow(row) && isValidColumn(column)) {
            return this.fields[row][column];
        }
        return null;
    }

    public void setField(int row, int column, Color newColor) {
        if (isValidRow(row) && isValidColumn(column)) {
            this.fields[row][column] = newColor;
        }
    }

    private boolean isValidRow(int row) {
        return row >= 0 && row < getRowsCount();
    }

    private boolean isValidColumn(int column) {
        return column >= 0 && column < getColumnsCount();
    }

    public void append(FallingBrick brick) {
        int rowShift = brick.getRowShift();
        int columnShift = brick.getColumnShift();
        for (int i = rowShift; i < rowShift + brick.getHeight(); i++) {
            for (int j = columnShift; j < columnShift + brick.getWidth(); j++) {
                if (brick.isOccupied(i, j)) {
                    this.setField(i, j, brick.getColor(i, j));
                }
            }
        }
    }

    public void reset() {
        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                setField(i, j, Brick.DEFAULT_COLOR);
            }
        }
    }

    public void put(boolean[][] blocks, Color blockColor) {
        int columnShift = (getColumnsCount() - blocks[0].length) / 2;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j]) {
                    setField(i, j + columnShift, blockColor);
                }
            }
        }
    }

    public boolean tryToDestroyLine() {
        for (int i = 0; i < getRowsCount(); i++) {
            if (isLineOfFullFields(i)) {
                destroyLine(i);
                return true;
            }
        }
        return false;
    }

    private boolean isLineOfFullFields(int row) {
        for (int i = 0; i < getColumnsCount(); i++) {
            if (!isOccupied(row, i)) {
                return false;
            }
        }
        return true;
    }

    private void destroyLine(int row) {
        for (int i = row - 1; i >= 0; i--) {
            System.arraycopy(fields[i], 0, fields[i + 1], 0, getColumnsCount());
        }
        for (int i = 0; i < getColumnsCount(); i++) {
            fields[0][i] = Brick.DEFAULT_COLOR;
        }
    }

    public boolean overlapsWith(FallingBrick brick) {
        for (int i = 0; i < this.getRowsCount(); i++) {
            for (int j = 0; j < this.getColumnsCount(); j++) {
                if (this.isOccupied(i, j) && brick.isOccupied(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasEqualSizeWith(Layer layer) {
        return getRowsCount() == layer.getRowsCount()
                && getColumnsCount() == layer.getColumnsCount();
    }

    public boolean isOccupied(int row, int column) {
        if (isValidRow(row) && isValidColumn(column)) {
            return getField(row, column) != Brick.DEFAULT_COLOR;
        }
        return false;
    }

    public Layer getMovedDown() {
        Layer layer = new Layer(getRowsCount(), getColumnsCount(), brick);
        for (int i = 0; i < this.getRowsCount() - 1; i++) {
            for (int j = 0; j < this.getColumnsCount(); j++) {
                if (isOccupied(i, j)) {
                    layer.setField(i + 1, j, getField(i, j));
                }
            }
        }
        return layer;
    }

    public boolean isOnBottom() {
        int bottomRow = getRowsCount() - 1;
        for (int i = 0; i < getColumnsCount(); i++) {
            if (isOccupied(bottomRow, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOnTop() {
        int topRow = 0;
        for (int i = 0; i < getColumnsCount(); i++) {
            if (isOccupied(topRow, i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Layer)) {
            return false;
        }

        Layer other = (Layer) obj;
        if (!hasEqualSizeWith(other)) {
            return false;
        }

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                if (getField(i, j) != other.getField(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Layer getMovedRight() {
        if (canMoveRight()) {
            Layer moved = new Layer(getRowsCount(), getColumnsCount(), brick);
            for (int i = 0; i < getRowsCount(); i++) {
                for (int j = getColumnsCount() - 1; j >= 0; j--) {
                    moved.setField(i, j + 1, this.getField(i, j));
                }
            }
            return moved;
        }
        return this;
    }

    public boolean canMoveRight() {
        for (int i = 0; i < getRowsCount(); i++) {
            if (isOccupied(i, getColumnsCount() - 1)) {
                return false;
            }
        }
        return true;
    }

    public Layer getMovedLeft() {
        if (canMoveLeft()) {
            Layer moved = new Layer(getRowsCount(), getColumnsCount(), brick);
            for (int i = 0; i < getRowsCount(); i++) {
                for (int j = 1; j < getColumnsCount(); j++) {
                    moved.setField(i, j - 1, getField(i, j));
                }
            }
            return moved;
        }
        return this;
    }

    public boolean canMoveLeft() {
        for (int i = 0; i < getRowsCount(); i++) {
            if (isOccupied(i, 0)) {
                return false;
            }
        }
        return true;
    }

    static public Color getDefaultColor() {
        return Brick.DEFAULT_COLOR;
    }

}
