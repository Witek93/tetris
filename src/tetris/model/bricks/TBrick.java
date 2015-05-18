package tetris.model.bricks;

import java.awt.Color;


public class TBrick extends Brick {

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
        private static final boolean[][][] VARIANTS = {
        {
            {true,  true,  true},
            {false, true, false}
        },
        {
            {true, false},
            {true, true },
            {true, false}
        },
        {
            {false, true, false},
            {true,  true, true }
        },
        {
            {false, true},
            {true,  true},
            {false, true}
        },
    };
    
    @Override
    Color getColors() {
        return new Color(184, 115, 51);
    }
}
