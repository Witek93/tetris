package tetris.model.bricks;


public abstract class Brick {
    private int rotation = 0;
    
    abstract boolean[][][] getVariants();
    
    public void rotate() {
        rotation = (rotation + 1) % getVariants().length;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : getVariant()) {
            for(boolean field : row) {
                if(field) {
                    sb.append('X');
                } else {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }
        
        return sb.toString();
    }

    
}
