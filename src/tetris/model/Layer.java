package tetris.model;

import java.awt.Color;


public class Layer {
    private final Color[][] fields;
    
    public Layer(int width, int height) {
        this.fields = new Color[height][];
        for(int i = 0; i < height; i++) {
            this.fields[i] = new Color[width];
            for(int j = 0; j < width; j++) {
                this.fields[i][j] = Color.white;
            }
        }
    }
    
    public int getHeight() {
        return this.fields.length;
    }
    
    public int getWidth() {
        return this.fields[0].length;
    }
    
    public Color getField(int x, int y) {
        return this.fields[y][x];
    }
    
    public void setField(int x, int y, Color color) {
        if(x >= 0 && y >= 0) {
            this.fields[y][x] = color;
        }
    }
    
    /*
    returns false when some colors of two layers are overlapping each other.
    returns false when layers' dimensions are not equal
    */
    public boolean overlapsWith(Layer layer) {
        if(layer.getWidth() != this.getWidth()) 
            return false;
        if(layer.getHeight() != this.getHeight())
            return false;
        
        for(int i = 0; i < this.getWidth(); i++) {
            for(int j = 0; j < this.getHeight(); j++) {
                if(isFullField(this.getField(i,j)) && isFullField(layer.getField(i,j))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isFullField(Color color) {
        return color != Color.white;
    }

    public Layer getMovedDownLayer() {
        Layer layer = new Layer(this.getWidth(), this.getHeight());
        for(int i = 0; i < this.getWidth(); i++) {
            for(int j = 0; j < this.getHeight() - 1; j++) {
                Color current = this.getField(i,j);
                if(isFullField(current)) {
                    layer.setField(i, j+1, current);
                }
            }
        }
        return layer;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Color[] row : fields) {
            for(Color field : row) {
                if(isFullField(field)) {
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
