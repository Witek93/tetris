package tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameFrame extends JFrame {

    private static final int MAX_BRICK_SIDE_VALUE = 4;

    private final BoardPanel gameBoard, nextBoard;
    private final JPanel scorePanel, nextPanel, optionPanel;
    private final JLabel score, linesDestroyed;
    private final JButton startButton, pauseButton;

    public GameFrame(int rowsCount, int columnsCount) {
        setTitle("Tetris game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 900);
        setFocusable(true);
        setMinimumSize(new Dimension(500, 500));
        
        this.score = createBigLabel("0");
        this.linesDestroyed = createBigLabel("0"); 
        this.startButton = new JButton("START");
        this.pauseButton = new JButton("PAUSE");

        this.gameBoard = new BoardPanel(rowsCount, columnsCount);
        gameBoard.setBackground(Color.red);
        gameBoard.setBorder(new LineBorder(Color.black, 3, true));
        add(gameBoard, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(3, 1));
        eastPanel.setBackground(Color.black);
        eastPanel.setPreferredSize(new Dimension(300, 600));
        add(eastPanel, BorderLayout.EAST);

        this.nextPanel = createNextPanel();
        this.nextBoard = new BoardPanel(MAX_BRICK_SIDE_VALUE, MAX_BRICK_SIDE_VALUE);
        nextPanel.add(nextBoard, BorderLayout.CENTER);
        eastPanel.add(nextPanel);
        
        this.scorePanel = createScorePanel();
        eastPanel.add(scorePanel);

        this.optionPanel = createOptionPanel();
        eastPanel.add(optionPanel);
    }
    
    
    public void addScore(int value) {
        long currentScore = Long.parseLong(score.getText());
        long newScore = currentScore + value;
        score.setText(String.valueOf(newScore));
    }
    
    public void addDestroyedLines(int value) {
        long currentLines = Long.parseLong(linesDestroyed.getText());
        long totalLinesDestroyed = currentLines + value;
        linesDestroyed.setText(String.valueOf(totalLinesDestroyed));
    }
    
    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }
    
    public void addPauseListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }
    

    private JPanel createNextPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.black, 3));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);
        
        JLabel label = createBigLabel("NEXT");
        panel.add(label, BorderLayout.NORTH);
        
        return panel;
    }

    private JPanel createScorePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.black, 3));
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(4, 1));
        
        JLabel pointsLabel = createBigLabel("POINTS");
        panel.add(pointsLabel);
        
        panel.add(score);
        
        JLabel linesLabel = createBigLabel("LINES DESTROYED");
        panel.add(linesLabel);
        
        panel.add(this.linesDestroyed);
        
        return panel;
    }

    private JPanel createOptionPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.black, 3));
        panel.setLayout(new GridLayout(2, 1));
        
        panel.add(startButton);
        startButton.setFont(new Font("Serif", Font.PLAIN, 32));
        
        panel.add(pauseButton);
        pauseButton.setFont(new Font("Serif", Font.PLAIN, 32));
        
        return panel;
    }
    
    private JLabel createBigLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setBorder(new LineBorder(Color.black, 1));
        label.setFont(new Font("Serif", Font.PLAIN, 28));
        return label;
    }

    public void updateBoardField(int row, int column, Color color) {
        gameBoard.updateField(row, column, color);
    }

    public void updateNextFieldPanel(boolean[][] nextBrick, Color color) {
        int nextRows = nextBrick.length, nextColumns = nextBrick[0].length;
        for (int i = 0; i < MAX_BRICK_SIDE_VALUE; i++) {
            for (int j = 0; j < MAX_BRICK_SIDE_VALUE; j++) {
                if (i < nextRows && j < nextColumns) {
                    if (nextBrick[i][j]) {
                        nextBoard.updateField(i, j, color);
                    } else {
                        nextBoard.resetField(i, j);
                    }
                } else {
                    nextBoard.resetField(i, j);
                }

            }
        }
    }
    
    public void setKeyListener(KeyListener listener) {
        this.addKeyListener(listener);
    }

    public void gameOverAlert() {
        JOptionPane.showMessageDialog(this, "Game over!", "Tetris", JOptionPane.INFORMATION_MESSAGE);
        this.revalidate();
    }

}
