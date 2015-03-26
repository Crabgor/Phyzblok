package view;

import controller.Controller;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameWindow extends JFrame
{
    private static final int PREF_W = 880;
    private static final int PREF_H = 880;

    private static GameWindow singleton;
    public static GameWindow getInstance()
    {
        if (singleton == null)
            singleton = new GameWindow("PhyzBlok");
        return singleton;
    }

    private GameWindow(String name) {
        super(name);
        setLocation(300, 300); // TODO: Change if necessary
        setSize(PREF_W, PREF_H + 22);
        setVisible(true);
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }


    public Dimension getSize()
    {
        return new Dimension(this.getContentPane().getWidth(), this.getContentPane().getHeight());
    }


    public float getModelScaleX()
    {
        Model m = Controller.getInstance().getModel();
        return this.getContentPane().getWidth() / Math.abs(m.RIGHT_WIDTH - m.LEFT_WIDTH);
    }


    public float getModelScaleY()
    {
        Model m = Controller.getInstance().getModel();
        return this.getContentPane().getHeight() / Math.abs(m.TOP_HEIGHT - m.BOTTOM_HEIGHT);
    }
}
