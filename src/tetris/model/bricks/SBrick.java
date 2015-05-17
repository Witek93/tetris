package tetris.model.bricks;

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
            {false, true },
            {true,  true },
            {true,  false}
        },
    };
}
