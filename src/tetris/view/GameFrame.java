package tetris.view;

import java.awt.Color;
import javax.swing.JFrame;


public class GameFrame extends JFrame {
    BoardPanel board;

    public GameFrame(int rowsCount, int columnsCount) {
        this.board = new BoardPanel(rowsCount, columnsCount);
        setTitle("Tetris game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        
        add(this.board);
    }
    
    public void updateField(int row, int column, Color color) {
        board.updateField(row, column, color);
    }
    
}
