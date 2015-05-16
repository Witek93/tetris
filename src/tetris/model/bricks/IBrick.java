package tetris.model.bricks;

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
}
