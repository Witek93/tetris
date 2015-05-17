package tetris.model;

import java.util.Random;
import tetris.model.bricks.*;


public class BrickFactory {
    private static final Random randomGen = new Random();
    private static final String[] shapes = {"I", "J", "L", "O", "S", "T", "Z"};
    
    static public Brick create() {
        String shape = shapes[randomGen.nextInt(shapes.length)];
        return create(shape);
    }
    
    private static Brick create(String name) {
        switch (name) {
            case "I": return new IBrick();
            case "J": return new JBrick();
            case "L": return new LBrick();
            case "O": return new OBrick();
            case "S": return new SBrick();
            case "T": return new TBrick();
            case "Z": return new ZBrick();
            default : return null;
        }
    }
}
