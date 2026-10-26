import javax.swing.*;
import java.awt.*;

public class BattleshipFrame extends JFrame {
    private final BattleshipGame game;
    private final JButton[][] buttons;
    private final JLabel missLabel;
    private final JLabel strikeLabel;
    private final JLabel totalMissLabel;
    private final JLabel totalHitLabel;

    public BattleshipFrame() {
        game = new BattleshipGame();
        setTitle("Battleship - Lab07C");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Top Status Panel
        JPanel statusPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        missLabel = new JLabel("Miss Counter (0-5): 0");
        strikeLabel = new JLabel("Strike Counter (0-3): 0");
        totalMissLabel = new JLabel("Total Miss Counter (0-83): 0");
        totalHitLabel = new JLabel("Total Hit Counter (0-17): 0");

        statusPanel.add(missLabel);
        statusPanel.add(strikeLabel);
        statusPanel.add(totalMissLabel);
        statusPanel.add(totalHitLabel);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusPanel, BorderLayout.NORTH);

        // Center Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(10, 10, 2, 2));
        buttons = new JButton[10][10];
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                buttons[r][c] = new JButton("~");
                buttons[r][c].setFont(new Font("Arial", Font.BOLD, 14));
                buttons[r][c].setBackground(new Color(173, 216, 230)); // Light blue wave
                final int row = r;
                final int col = c;
                buttons[r][c].addActionListener(_ -> handleCellClick(row, col));
                gridPanel.add(buttons[r][c]);
            }
        }
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(gridPanel, BorderLayout.CENTER);

        // South Control Panel
        JPanel controlPanel = new JPanel();
        JButton playAgainBtn = new JButton("Play Again");
        JButton quitBtn = new JButton("Quit");

        playAgainBtn.addActionListener(_ -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to start a new game?", "Play Again", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                game.resetGame();
                resetBoardUI();
                updateLabels();
            }
        });

        quitBtn.addActionListener(_ -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        controlPanel.add(playAgainBtn);
        controlPanel.add(quitBtn);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void handleCellClick(int row, int col) {
        if (game.isGameOver()) return;

        JButton btn = buttons[row][col];
        btn.setEnabled(false);

        String result = game.fireAt(row, col);

        if (result.equals("HIT") || result.equals("SUNK")) {
            btn.setText("X");
            btn.setBackground(Color.RED);
            if (result.equals("SUNK")) {
                JOptionPane.showMessageDialog(this, "A ship was sunk!");
            }
        } else if (result.equals("MISS") || result.equals("STRIKE")) {
            btn.setText("M");
            btn.setBackground(Color.YELLOW);
            if (result.equals("STRIKE")) {
                JOptionPane.showMessageDialog(this, "5 misses reached! A strike has been added.");
            }
        }
        updateLabels();
    }

    private void resetBoardUI() {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                buttons[r][c].setText("~");
                buttons[r][c].setBackground(new Color(173, 216, 230));
                buttons[r][c].setEnabled(true);
            }
        }
    }

    private void updateLabels() {
        missLabel.setText("Miss Counter (0-5): " + game.getMissCounter());
        strikeLabel.setText("Strike Counter (0-3): " + game.getStrikeCounter());
        totalMissLabel.setText("Total Miss Counter (0-83): " + game.getTotalMissCounter());
        totalHitLabel.setText("Total Hit Counter (0-17): " + game.getTotalHitCounter());
    }

}