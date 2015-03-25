package view;

import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame
{
    private static final int PREF_W = 1000;
    private static final int PREF_H = 940;


    public GameWindow(String name) {
        super(name);
        setLocation(900, 300); // TODO: Change if necessary
        setSize(PREF_W, PREF_H);
        setVisible(true);
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}
