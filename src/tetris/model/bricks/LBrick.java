package tetris.model.bricks;

public class LBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {true},
            {true},
            {true, true}
        },     
        {
            {false, false, true},
            {true,  true,  true}
        },
        {
            { true,  true},
            {false,  true},
            {false,  true}
        },
        {
            {true,  true,  true},
            {true},
        },
    };
}
