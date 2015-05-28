package tetris.model.bricks;

import java.awt.Color;

public class JBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {false, true},
            {false, true},
            {true,  true}
        },     
        {
            {true,  true,  true},
            {false, false, true}
        },
        {
            {true, true },
            {true, false},
            {true, false}
        },
        {
            {true, false, false},
            {true, true,  true}
        },
    };
    
    @Override
    Color getColors() {
        return new Color(249, 224, 75);
    }
}

