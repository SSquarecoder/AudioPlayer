package org.example;
import javax.swing.*;
import java.awt.*;

public class MenuBar {
        public static void main(String[] args) {
            JFrame f = new JFrame("MenuBar Example");
            f.setSize(600,500);
            f.setLocationRelativeTo (null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JMenuBar menubar = new JMenuBar();
            JMenu file = new JMenu("Ready For the game");

            JMenuItem i1 = new JMenuItem("Level 1");
            JMenuItem i2 = new JMenuItem("Level 2");
//            JMenuItem i3 = new JMenuItem("Dont want to play the game");

            file.add(i1);
            file.add(i2);
//            file.add(i3);
            menubar.add(file);

            JMenu nogame = new JMenu("No game");

            JMenuItem i4 = new JMenuItem("Might be restart");
            JMenuItem i5 = new JMenuItem("Quit");
            nogame.add(i4);
            nogame.add(i5);

            file.add(nogame);

            f.setJMenuBar(menubar);
            f.setVisible(true);
        }
}
