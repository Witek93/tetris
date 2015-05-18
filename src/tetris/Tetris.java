package tetris;

import java.awt.Color;
import javax.swing.JOptionPane;
import tetris.model.GameBoard;
import tetris.view.GameFrame;


public class Tetris {


    public static void main(String[] args) throws InterruptedException {

        int rows = 20, column = 9;
        
        GameFrame gf = new GameFrame(rows, column);
        GameBoard game = new GameBoard(rows, column);
        
        game.generateNewBrick();
        gf.setVisible(true);
        
        
        for(int a = 0; a < 10; a++) {
            game.generateNewBrick();
            
            while(game.tryToMoveDown()) {
                Thread.sleep(100);
                
                // aktualizowanie stanu planszy
                for(int i = 0; i < rows; i++) {
                    for(int j = 0; j < column; j++) {
                        Color color = game.getColorOf(i, j);
                        gf.updateField(i,j,color);
                    }
                }
                
            }
            if(game.activeLayer.isOnTop()) {
                JOptionPane.showMessageDialog(gf, "Game over!", "Tetris", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            

        
        }
        
       
//        for(int i = 0; i < 6; i++) {
//            game.generateNewBrick();
//            
//            while(game.tryToMoveDown()) {
//                Thread.sleep(500);
//                System.out.println(game);
//            }
//            if(game.activeLayer.isOnTop()) {
//                System.out.println("Gra zakończona!");
//                break;
//            }
//            
//        }
        

    }
    
}
