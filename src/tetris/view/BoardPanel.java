package tetris.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class BoardPanel extends JPanel {
    JPanel[][] fields;
    
    public BoardPanel(int rowsCount, int columnsCount) {
        fields = new JPanel[rowsCount][columnsCount];
        
        setLayout(new GridLayout(rowsCount, columnsCount, 1, 1));
        
        for(int i = 0; i < rowsCount; i++) {
            for(int j = 0; j < columnsCount; j++) {
                fields[i][j] = new JPanel();
                fields[i][j].setBackground(Color.white);
                add(fields[i][j]);
            }
        }
        
        
    }
    
    public void updateField(int row, int column, Color color) {
        getField(row,column).setBackground(color);
    }
    
    private JPanel getField(int row, int column) {
        return this.fields[row][column];
    }
    
}
