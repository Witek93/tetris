package tetris;

import tetris.controller.GameController;
import tetris.model.GameBoard;
import tetris.view.GameFrame;


public class Tetris {


    public static void main(String[] args) throws InterruptedException {

        int rows = 20, column = 6;
        
        GameFrame view = new GameFrame(rows, column);
        view.setVisible(true);
        
        GameBoard model = new GameBoard(rows, column);
        
        GameController controller = new GameController(model, view);
        controller.start();
        
    }
    
}
