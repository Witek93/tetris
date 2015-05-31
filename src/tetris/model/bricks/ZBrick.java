package tetris.model.bricks;

import java.awt.Color;

public class ZBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {true,  true, false},
            {false, true, true },
        },     
        {
            {false, true },
            {true,  true },
            {true,  false}
        },
    };
    
    @Override
    public Color getColor() {
        return new Color(213, 173, 66);
    }
}
