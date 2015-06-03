package tetris.model.bricks;

import java.awt.Color;

public abstract class Brick {

    private int rotation = 0;
    static public Color DEFAULT_COLOR = Color.white;

    protected abstract boolean[][][] getVariants();

    public abstract Color getColor();

    public void rotate() {
        rotation = (rotation + 1) % getVariants().length;
    }

    public void rotateBack() {
        rotation = (getVariants().length + rotation - 1) % getVariants().length;
    }

    public int getHeight() {
        return getVariant().length;
    }

    public int getWidth() {
        return getVariant()[0].length;
    }

    public boolean[][] getVariant() {
        return getVariants()[rotation];
    }

    public Color getColor(int row, int column) {
        if (row >= 0 && row < getHeight() && column >= 0 && column < getWidth()) {
            if (getVariant()[row][column]) {
                return getColor();
            }
        }
        return DEFAULT_COLOR;
    }
}
