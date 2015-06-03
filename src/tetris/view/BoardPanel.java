package tetris.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class BoardPanel extends JPanel {
    private static final Color DEFAULT_COLOR = Color.white;
    
    private final JPanel[][] fields;
    
    public BoardPanel(int rowsCount, int columnsCount) {
        this.fields = new JPanel[rowsCount][columnsCount];       
        setLayout(new GridLayout(rowsCount, columnsCount, 1, 1));
        
        for(int i = 0; i < rowsCount; i++) {
            for(int j = 0; j < columnsCount; j++) {
                fields[i][j] = new JPanel();
                fields[i][j].setBackground(DEFAULT_COLOR);
                add(fields[i][j]);
            }
        }
    }
    
    public void updateField(int row, int column, Color color) {
        getField(row,column).setBackground(color);
    }
    
    public void resetField(int row, int column) {
        getField(row, column).setBackground(DEFAULT_COLOR);
    }
    
    private JPanel getField(int row, int column) {
        return this.fields[row][column];
    }
    
}
