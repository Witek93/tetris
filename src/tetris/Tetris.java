package tetris;

import java.awt.Color;
import tetris.model.GameBoard;
import tetris.model.Layer;


public class Tetris {


    public static void main(String[] args) throws InterruptedException {

        GameBoard game = new GameBoard(10, 5);
        
        game.defaultLayer.setField(9, 2, Color.yellow);
        
        game.activeLayer.setField(1, 1, Color.yellow);
        game.activeLayer.setField(1, 2, Color.yellow);
        game.activeLayer.setField(2, 1, Color.yellow);
        game.activeLayer.setField(0, 2, Color.yellow);
        
        for(int i = 0; i < 6; i++) {
            while(game.moveDownActiveBoard()) {
                Thread.sleep(300);
                System.out.println(game);
            }
            
            game.activeLayer = new Layer(10, 5);
            game.activeLayer.setField(0, i, Color.red);
            
        }
        

    }
    
}
