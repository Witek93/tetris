package tetris.model;

import java.awt.Color;
import tetris.model.bricks.Brick;


public class GameBoard {
    private Layer activeLayer, defaultLayer;
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
    
    public int tryToDestroyLines() {
        int destroyedLines = 0;
        while(defaultLayer.tryToDestroyLine()) {
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
    
}
