package tetris;

import tetris.model.bricks.Brick;
import tetris.model.bricks.TBrick;


public class Tetris {


    public static void main(String[] args) {
        Brick brick = new TBrick();
        
        System.out.println(brick); // brak rotacji
        
        brick.rotate();
        System.out.println(brick); // rotacja 90 stopni
        
        brick.rotate();
        System.out.println(brick); // rotacja 180 stopni
        
        brick.rotate();
        System.out.println(brick); // rotacja 270 stopni
    }
    
}
