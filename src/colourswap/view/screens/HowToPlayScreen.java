package colourswap.view.screens;

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
 * The screen that displays game instructions. This screen contains the 'how to play' title, an image, a short
 * description, and a button to advance to the next screen.
 */
public class HowToPlayScreen extends JPanel {

    public HowToPlayScreen(MainWindow mainWindow, String imagePath, String text, String destination) {
        this.setLayout(new GridBagLayout());

        // This displays the 'how to play' text.
        JLabel titleLabel = new JLabel("how to play");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(0, 0, 50, 0); // Separate from content panel
        this.add(titleLabel, c);

        // This panel contains all of the other stuff? TODO
        JPanel contentPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridy = 1;
        this.add(contentPanel, c);

        // This displays the image
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            c = new GridBagConstraints();
            c.gridx = 0;
            c.insets = new Insets(0, 0, 0, 100); // Separate from text panel
            contentPanel.add(imageLabel, c);
        } catch (IOException e) {
            // Maybe not great to ignore the problem?
        }

        // This is the panel for the instructions?
        JPanel textPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 1;
        contentPanel.add(textPanel, c);

        // This displays the instructions
        JLabel textLabel = new JLabel(text);
        textLabel.setPreferredSize(new Dimension(200, 50));
        c = new GridBagConstraints();
        c.gridy = 0;
        textPanel.add(textLabel, c);

        // This displays the button that goes to the next screen
        JButton button = new JButton(">");
        button.setMaximumSize(new Dimension(200, 50));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) mainWindow.getLayout();
                layout.show(mainWindow, destination);
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        c = new GridBagConstraints();
        c.gridy = 1;
        textPanel.add(button, c);

    }

}
