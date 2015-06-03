package tetris.model.bricks;

import java.awt.Color;

public class IBrick extends Brick{

    @Override
    protected boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {true,  true,  true, true}
        },     
        {
            {true},
            {true},
            {true},
            {true}
        }
    };

    @Override
    public Color getColor() {
        return new Color(173, 17, 17);
    }
}
