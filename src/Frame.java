import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final int BOARD_SIZE = 10;
    private JButton[][] boardButtons;
    private BattleshipGame game;

    private JLabel totalHitLabel;
    private JLabel totalMissLabel;
    private JLabel currentMissLabel;
    private JLabel strikesLabel;

    public Frame() {
        setTitle("Battleship Game");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createCenterFrame();

        boardButtons = new JButton[BOARD_SIZE][BOARD_SIZE];
        game = new BattleshipGame();

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initializeBoard(boardPanel);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(4, 3));
        initializeStatusPanel(statusPanel);

        JButton playAgainButton = new JButton("Play Again");
        JButton quitButton = new JButton("Quit");

        playAgainButton.addActionListener(e -> playAgain());
        quitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void initializeBoard(JPanel boardPanel) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton("");
                final int x = row;
                final int y = col;

                button.addActionListener(e -> fireMissile(x, y, button));

                boardButtons[row][col] = button;
                boardPanel.add(button);
            }
        }
    }

    private void initializeStatusPanel(JPanel statusPanel) {
        currentMissLabel = new JLabel("Current Misses: 0");
        totalHitLabel = new JLabel("Total Hits: 0");
        totalMissLabel = new JLabel("Total Misses: 0");
        strikesLabel = new JLabel("Strikes: 0");

        statusPanel.add(new JLabel("Current Misses:"));
        statusPanel.add(currentMissLabel);
        statusPanel.add(new JLabel("Total Hits:"));
        statusPanel.add(totalHitLabel);
        statusPanel.add(new JLabel("Total Misses:"));
        statusPanel.add(totalMissLabel);
        statusPanel.add(new JLabel("Strikes:"));
        statusPanel.add(strikesLabel);
    }

    private void fireMissile(int x, int y, JButton button) {
        boolean hit = game.fireMissile(x, y);
        if (hit) {
            button.setText("X");
        } else {
            button.setText("M");
        }

        updateStatus();

        if(game.allShipsSunk()) {
            JOptionPane.showMessageDialog(this, "Congrats! All ships sunk!");
            playAgain();
        }

        if(game.isGameOver()) {
            JOptionPane.showMessageDialog(this, "Game Over!");
            playAgain();
        }
    }

    private void updateStatus() {
        totalHitLabel.setText("Total Hits: " + game.getTotalHits());
        totalMissLabel.setText("Total Misses: " + game.getTotalMisses());
        currentMissLabel.setText("Current Misses: " + game.getCurrentMisses());
        strikesLabel.setText("Strikes: " + game.getStrikes());
    }

    private void playAgain() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);

        if(result == JOptionPane.YES_OPTION) {
            resetGame();
        }
    }

    private void resetGame() {
        game = new BattleshipGame();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boardButtons[row][col].setText("~");
            }
        }

        updateStatus();
    }

    private void createCenterFrame(){
        //screen dimensions
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        //center frame
        setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        setLocation(screenWidth / 8, screenHeight / 8);
    }
}
