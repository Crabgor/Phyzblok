package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


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
        JFrame frame = new JFrame("GameDemo");
        frame.setLocation(1200, 400);
        frame.setSize(PREF_W, PREF_H);
        //frame.pack();  //sizes so everything fits
        frame.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ConstructionHelper();



    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}
