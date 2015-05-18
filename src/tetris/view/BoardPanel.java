package tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class BoardPanel extends JPanel {
    private final JPanel fieldsPanel;
    private final JPanel[][] fields;
    
    public BoardPanel(int rowsCount, int columnsCount) {
        this.fields = new JPanel[rowsCount][columnsCount];
        this.fieldsPanel = new JPanel();
        setLayout(new GridLayout());
        add(fieldsPanel);
        
        fieldsPanel.setLayout(new GridLayout(rowsCount, columnsCount, 1, 1));
        
        for(int i = 0; i < rowsCount; i++) {
            for(int j = 0; j < columnsCount; j++) {
                fields[i][j] = new JPanel();
                fields[i][j].setBackground(Color.white);
                fieldsPanel.add(fields[i][j]);
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
