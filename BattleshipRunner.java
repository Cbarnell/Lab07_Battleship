import javax.swing.*;

void main() {
    SwingUtilities.invokeLater(() -> {
        BattleshipFrame frame = new BattleshipFrame();
        frame.setVisible(true);
    });
}