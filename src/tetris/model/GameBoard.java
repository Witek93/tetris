package tetris.model;

import java.awt.Color;


public class GameBoard {
    public Layer activeLayer, defaultLayer;

    public GameBoard(int width, int height) {
        this.defaultLayer = new Layer(width, height);
        this.activeLayer = new Layer(width, height);
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
        for(int i = 0; i < defaultLayer.getHeight(); i++) {
            for(int j = 0; j < defaultLayer.getWidth(); j++) {
                if(defaultLayer.getField(j,i) != Color.white 
                        || activeLayer.getField(j,i) != Color.white) {
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
