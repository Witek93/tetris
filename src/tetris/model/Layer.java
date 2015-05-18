package tetris.model;

import java.awt.Color;

public class Layer {

    private final Color[][] fields;
    private static final Color defaultColor = Color.white;

    public Layer(int rowsCount, int columnsCount) {
        this.fields = new Color[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                this.fields[i][j] = defaultColor;
            }
        }
    }

    public int getRowsCount() {
        return this.fields.length;
    }

    public int getColumnsCount() {
        return this.fields[0].length;
    }

    public Color getField(int row, int column) {
        return this.fields[row][column];
    }

    public void setField(int row, int column, Color newColor) {
        if (row >= 0 && row < getRowsCount()
                && column >= 0 && column < getColumnsCount()) {
            this.fields[row][column] = newColor;
        }
    }

    public void amendWith(Layer layer) {
        if (hasEqualSizeWith(layer)) {
            for (int i = 0; i < getRowsCount(); i++) {
                for (int j = 0; j < getColumnsCount(); j++) {
                    Color field = layer.getField(i, j);
                    if (isFullField(field)) {
                        setField(i, j, field);
                    }
                }
            }
        }
    }

    public void reset() {
        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                setField(i, j, defaultColor);
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

    /*
     returns false when some colors of two layers are overlapping each other.
     returns false when layers' sizes are not equal
     */
    public boolean overlapsWith(Layer layer) {
        if (!this.hasEqualSizeWith(layer)) {
            return false;
        }

        for (int i = 0; i < this.getRowsCount(); i++) {
            for (int j = 0; j < this.getColumnsCount(); j++) {
                if (isFullField(this.getField(i, j)) && isFullField(layer.getField(i, j))) {
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

    public static boolean isFullField(Color color) {
        return color != defaultColor;
    }

    public Layer getMovedDownLayer() {
        Layer layer = new Layer(this.getRowsCount(), this.getColumnsCount());

        for (int i = 0; i < this.getRowsCount() - 1; i++) {
            for (int j = 0; j < this.getColumnsCount(); j++) {
                Color current = this.getField(i, j);
                if (isFullField(current)) {
                    layer.setField(i + 1, j, current);
                }
            }
        }
        return layer;
    }

    public boolean isOnBottom() {
        for (Color field : fields[getRowsCount() - 1]) {
            if (isFullField(field)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isOnTop() {
        for (Color field : fields[0]) {
            if (isFullField(field)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Color[] row : fields) {
            for (Color field : row) {
                if (isFullField(field)) {
                    sb.append('X');
                } else {
                    sb.append('-');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
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

}
