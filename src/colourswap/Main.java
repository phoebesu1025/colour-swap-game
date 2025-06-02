package colourswap;

import colourswap.controller.GameController;
import colourswap.view.MainWindow;

import javax.swing.*;

/**
 * Entry point for Colour Swap application.
 */
public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Colour Swap");

                // The controller used to create and run games
                GameController controller = new GameController();

                // The main window, containing the UI to display games, and allowing key events to provide user input
                MainWindow mainWindow = new MainWindow(controller);

                frame.add(mainWindow);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }

}
