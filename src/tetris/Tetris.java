package tetris;

import java.awt.Color;
import tetris.model.GameBoard;
import tetris.model.Layer;
import tetris.model.bricks.*;


public class Tetris {


    public static void main(String[] args) throws InterruptedException {

        GameBoard game = new GameBoard(10, 5);
       
        
        for(int i = 0; i < 6; i++) {
            game.generateNewBrick();
            
            while(game.moveDownActiveBoard()) {
                Thread.sleep(500);
                System.out.println(game);
            }
            
        }
        

    }
    
}
