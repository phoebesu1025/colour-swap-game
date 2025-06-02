package colourswap.view.screens;

import colourswap.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The screen that displays the game over message. This screen contains the 'game over' title, the player's score, and
 * a button to return to the title screen.
 */
public class GameOverScreen extends JPanel {

    private JLabel scoreLabel;

    /**
     * The panel that is shown when the game is over.
     */
    public GameOverScreen(MainWindow mainWindow) {
        this.setLayout(new GridBagLayout());

        // This displays the 'game over' text
        JLabel titleLabel = new JLabel("game over");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(0, 0, 50, 0); // Separate from final score
        this.add(titleLabel, c);

        // This displays the final score
        this.scoreLabel = new JLabel();
        c = new GridBagConstraints();
        c.gridy = 1;
        c.insets = new Insets(0, 0, 150, 0); // Separate from button
        this.add(this.scoreLabel, c);

        // This displays the button that returns to the title screen
        JButton button = new JButton("title screen >");
        button.setPreferredSize(new Dimension(200, 50));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) mainWindow.getLayout();
                layout.show(mainWindow, "title");
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        c = new GridBagConstraints();
        c.gridy = 2;
        this.add(button, c);

    }

    public void setScore(int score) {
        this.scoreLabel.setText("score: " + score);
    }

}
