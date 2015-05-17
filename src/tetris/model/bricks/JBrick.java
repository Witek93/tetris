package tetris.model.bricks;

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
            {true, true,  true},
            {false,false, true}
        },
        {
            {true, true},
            {true},
            {true}
        },
        {
            {true},
            {true,  true,  true}
        },
    };
}

