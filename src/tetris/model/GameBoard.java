package tetris.model;

import java.awt.Color;


public class GameBoard {
    public Layer activeLayer, defaultLayer;

    public GameBoard(int rowCount, int columnCount) {
        this.defaultLayer = new Layer(rowCount, columnCount);
        this.activeLayer = new Layer(rowCount, columnCount);
    }
    
    public boolean moveDownActiveBoard() {
        Layer layer = activeLayer.getMovedDownLayer();
        if(layer.overlapsWith(defaultLayer)) {
            return false;
        }
        activeLayer = layer;
        return true;
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
