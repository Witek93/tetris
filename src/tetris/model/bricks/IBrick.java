package tetris.model.bricks;

import java.awt.Color;

public class IBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
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
        },
    };

    @Override
    Color getColors() {
        return new Color(173, 17, 17);
    }
}
