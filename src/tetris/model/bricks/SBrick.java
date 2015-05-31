package tetris.model.bricks;

import java.awt.Color;

public class SBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {false, true, true },
            {true,  true, false}
        },     
        {
            {true,  false},
            {true,  true },
            {false, true }
        },
    };
    
    @Override
    public Color getColor() {
        return new Color(195, 176, 145);
    }
}
