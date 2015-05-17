package tetris;

import java.awt.Color;
import tetris.model.GameBoard;


public class Tetris {


    public static void main(String[] args) throws InterruptedException {

        GameBoard game = new GameBoard(5, 10);
        
        game.defaultLayer.setField(2, 9, Color.yellow);
        
        game.activeLayer.setField(1, 0, Color.yellow);
        game.activeLayer.setField(1, 1, Color.yellow);
        game.activeLayer.setField(2, 0, Color.yellow);
        game.activeLayer.setField(0, 1, Color.yellow);
        
        
        
        while(game.moveDownActiveBoard()) {
            Thread.sleep(1000);
            System.out.println(game);
        }

    }
    
}
