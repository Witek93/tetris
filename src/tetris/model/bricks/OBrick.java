package tetris.model.bricks;

public class OBrick extends Brick{

    @Override
    boolean[][][] getVariants() {
        return VARIANTS;
    }
    
    private static final boolean[][][] VARIANTS = {
        {
            {true, true},
            {true, true}
        },     
    };
}
