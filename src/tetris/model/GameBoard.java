package tetris.model;

import java.awt.Color;
import java.util.Random;
import tetris.model.bricks.Brick;


public class GameBoard {
    private Layer activeLayer, defaultLayer;
    private Brick currentBrick;
    private final Random randomGen;

    public GameBoard(int rowCount, int columnCount) {
        this.defaultLayer = new Layer(rowCount, columnCount);
        this.activeLayer = new Layer(rowCount, columnCount);
        this.currentBrick = null;
        this.randomGen = new Random();
    }
    
    public int getRowsCount() {
        return defaultLayer.getRowsCount();
    }
    
    public int getColumnsCount() {
        return defaultLayer.getColumnsCount();
    }
    
    public boolean tryToMoveDown() {
        if(activeLayer.isOnBottom()) {
            mergeLayers();
            return false;
        } else {
            Layer layer = activeLayer.getMovedDownLayer();

            if(layer.overlapsWith(defaultLayer)) {
                mergeLayers();
                return false;
            } else {
                activeLayer = layer;
                return true;
            }
        }
    }
    
    private void mergeLayers() {
        defaultLayer.amendWith(activeLayer);
    }
    
    public void generateNewBrick() {
        currentBrick = BrickFactory.create();
        activeLayer.reset();
        activeLayer.put(currentBrick.getVariant(), getRandomColor());
    }
    
    public Color getRandomColor() {
        int red = randomGen.nextInt(256);
        int green = randomGen.nextInt(256);
        int blue = randomGen.nextInt(256);
        return new Color(red, green, blue);
    }
    
    public Color getColorOf(int row, int column) {
        Color color = defaultLayer.getField(row, column);
        if(Layer.isFullField(color)) {
            return color;
        } else {
            return activeLayer.getField(row, column);
        }
    }
    
    public boolean isGameOver() {
        return activeLayer.isOnTop();
    }

    @Override
    public String toString() {
                StringBuilder sb = new StringBuilder();
        for(int i = 0; i < defaultLayer.getRowsCount(); i++) {
            for(int j = 0; j < defaultLayer.getColumnsCount(); j++) {
                if(defaultLayer.getField(i,j) != Color.white 
                        || activeLayer.getField(i,j) != Color.white) {
                    sb.append('X');
                } else {
                    sb.append('-');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
}
