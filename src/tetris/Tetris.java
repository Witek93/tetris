package tetris;

import java.awt.Color;
import tetris.model.GameBoard;


public class Tetris {


    public static void main(String[] args) throws InterruptedException {

        GameBoard game = new GameBoard(10, 5);
        
        game.defaultLayer.setField(9, 2, Color.yellow);
        
        game.activeLayer.setField(1, 1, Color.yellow);
        game.activeLayer.setField(1, 2, Color.yellow);
        game.activeLayer.setField(2, 1, Color.yellow);
        game.activeLayer.setField(0, 2, Color.yellow);
        
        
        while(game.moveDownActiveBoard()) {
            Thread.sleep(1000);
            System.out.println(game);
        }

    }
    
}
