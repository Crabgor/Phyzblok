package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class GameWindow extends JFrame
{
    private static final int PREF_W = 800;
    private static final int PREF_H = PREF_W;
    /*private void ConstructionHelper(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //AddSquares(posX, posY, hight, with);
    }*/
    public GameWindow(String name) {
        super(name);
        setLocation(1200, 400); // TODO: Change
        setSize(PREF_W, PREF_H);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}
