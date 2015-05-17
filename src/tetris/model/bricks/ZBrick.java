package tetris.model.bricks;

public class ZBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {true,  true},
            {false, true,  true},
        },     
        {
            {false, true},
            {true,  true},
            {true}
        },
    };
}
