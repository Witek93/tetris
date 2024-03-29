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
    private final JPanel scorePanel, nextBrickPanel, buttonsPanel, optionsPanel;
    private final JLabel pointsLabel, linesLabel;
    private final JButton startButton, pauseButton;

    public GameFrame(int rowsCount, int columnsCount) {
        this.gameBoard = new BoardPanel(rowsCount, columnsCount);    
        this.optionsPanel = new JPanel();
        this.nextBrickPanel = new JPanel();
        this.nextBoard = new BoardPanel(MAX_BRICK_SIDE_VALUE, MAX_BRICK_SIDE_VALUE);        
        this.scorePanel = new JPanel();
        this.pointsLabel = createBigLabel("0");
        this.linesLabel = createBigLabel("0");       
        this.buttonsPanel = new JPanel();
        this.startButton = new JButton("START");
        this.pauseButton = new JButton("PAUSE");

        initGameFrame();
        initOptionsPanel();
        initNextBrickPanel();
        initScorePanel();
        initButtonsPanel();
    }

    private void initGameFrame() {
        setTitle("Tetris game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 700);
        setFocusable(true);
        setMinimumSize(new Dimension(500, 500));
        gameBoard.setBorder(new LineBorder(Color.black, 3, true));
        add(gameBoard, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.EAST);
    }

    private void initOptionsPanel() {
        optionsPanel.setLayout(new GridLayout(3, 1));
        optionsPanel.setPreferredSize(new Dimension(300, 600));
        
        optionsPanel.add(nextBrickPanel);
        optionsPanel.add(scorePanel);
        optionsPanel.add(buttonsPanel);
    }

    public void setScore(String newScore) {
        pointsLabel.setText(newScore);
    }

    public void setDestroyedLines(String newDestroyedLines) {
        linesLabel.setText(newDestroyedLines);
    }

    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void addPauseListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }

    private void initNextBrickPanel() {
        nextBrickPanel.setBorder(new LineBorder(Color.black, 3));
        nextBrickPanel.setLayout(new BorderLayout());
        nextBrickPanel.setBackground(Color.lightGray);

        JLabel label = createBigLabel("NEXT");
        nextBrickPanel.add(label, BorderLayout.NORTH);
        nextBrickPanel.add(nextBoard, BorderLayout.CENTER);
    }

    private void initScorePanel() {
        scorePanel.setBorder(new LineBorder(Color.black, 3));
        scorePanel.setBackground(Color.white);
        scorePanel.setLayout(new GridLayout(4, 1));

        JLabel pointsLabelDescription = createBigLabel("POINTS");
        JLabel linesLabelDescription = createBigLabel("LINES DESTROYED");

        scorePanel.add(pointsLabelDescription);
        scorePanel.add(this.pointsLabel);
        scorePanel.add(linesLabelDescription);
        scorePanel.add(this.linesLabel);
    }

    private void initButtonsPanel() {
        buttonsPanel.setBorder(new LineBorder(Color.black, 3));
        buttonsPanel.setLayout(new GridLayout(2, 1));

        startButton.setFont(new Font("Serif", Font.PLAIN, 32));
        pauseButton.setFont(new Font("Serif", Font.PLAIN, 32));
        
        buttonsPanel.add(startButton);
        buttonsPanel.add(pauseButton);
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
