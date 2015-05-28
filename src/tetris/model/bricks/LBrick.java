package tetris.model.bricks;

import java.awt.Color;

public class LBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {true, false},
            {true, false},
            {true, true}
        },     
        {
            {false, false, true},
            {true,  true,  true}
        },
        {
            {true,   true},
            {false,  true},
            {false,  true}
        },
        {
            {true, true,  true},
            {true, false, false},
        },
    };
    
    @Override
    Color getColors() {
        return new Color(233, 107, 57);
    }
}
