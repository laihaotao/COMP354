package ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() throws HeadlessException {
        this.setTitle("Pokemon TCG");
        this.setSize(800, 800);
        this.setVisible(true);

        // when the window closing, exit the game
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
