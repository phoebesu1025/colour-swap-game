package colourswap.view.screens;

import colourswap.controller.GameController;
import colourswap.controller.GameMode;
import colourswap.view.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The screen that displays the initial menu. The menu contains a cover image on the left, a button for how to play,
 * and buttons to start a new game.
 */
public class TitleScreen extends JPanel {

    public TitleScreen(MainWindow mainWindow, GameController gameController) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c;

        // This displays the image
        try {
            BufferedImage image = ImageIO.read(new File("assets/title-screen.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            c = new GridBagConstraints();
            c.gridx = 0;
            c.insets = new Insets(0, 0, 0, 150); // Separate from button panel
            this.add(imageLabel, c);
        } catch (IOException e) {
            // Do nothing
        }

        // This is the panel for the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 1;
        this.add(buttonPanel, c);

        // This displays the 'how to play' button
        JButton howToPlayButton = this.createButton("how to play >", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.showScreen("how to play 1");
            }
        });
        c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(30, 0, 30, 0); // Separate from other buttons
        buttonPanel.add(howToPlayButton, c);

        // This displays the 'single-player' button
        JButton singlePlayerButton = this.createButton("single-player >", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.startNewGame(GameMode.SINGLE_PLAYER);
            }
        });
        c = new GridBagConstraints();
        c.gridy = 1;
        c.insets = new Insets(30, 0, 30, 0); // Separate from other buttons
        buttonPanel.add(singlePlayerButton, c);

        // This displays the 'play with robot' button
        JButton robotPlayerButton = this.createButton("play with robot >", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.startNewGame(GameMode.PLAY_WITH_ROBOT);
            }
        });
        c = new GridBagConstraints();
        c.gridy = 2;
        c.insets = new Insets(30, 0, 30, 0); // Separate from other buttons
        buttonPanel.add(robotPlayerButton, c);

    }

    private JButton createButton(String text, ActionListener actionListener) {
        // Common code for buttons
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(300, 50));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        button.addActionListener(actionListener);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

}
