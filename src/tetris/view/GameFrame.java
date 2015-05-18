package tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class GameFrame extends JFrame {
    private final BoardPanel gameBoard;
    private final JPanel scoreBoard;

    public GameFrame(int rowsCount, int columnsCount) {
        setTitle("Tetris game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 900);
        
        this.gameBoard = new BoardPanel(rowsCount, columnsCount);
        gameBoard.setBackground(Color.red);
        add(gameBoard, BorderLayout.CENTER);
        
        this.scoreBoard = new JPanel();
        scoreBoard.setBackground(Color.black);
        scoreBoard.setPreferredSize(new Dimension(300, 600));
        add(scoreBoard, BorderLayout.EAST);
    }
    
    public void updateField(int row, int column, Color color) {
        gameBoard.updateField(row, column, color);
    }
    
    public void gameOverAlert() {
        JOptionPane.showMessageDialog(this, "Game over!", "Tetris", JOptionPane.INFORMATION_MESSAGE);
        this.revalidate();
    }
    
}
