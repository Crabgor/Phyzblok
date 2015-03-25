package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class GameWindow extends JFrame
{
    private static final int PREF_W = 880;//1000;
    private static final int PREF_H = 902;//940;


    public GameWindow(String name) {
        super(name);
        setLocation(300, 300); // TODO: Change if necessary
        setSize(PREF_W, PREF_H);
        setVisible(true);
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentListener(new ComponentAdapter()
        {
            /**
             * Invoked when the component's size changes.
             *
             * @param e
             */
            @Override
            public void componentResized(ComponentEvent e)
            {
                super.componentResized(e);
                GameWindow w = (GameWindow) e.getComponent();
                System.out.println("CONTENT PANE: " + w.getContentPane().getHeight() + "    " + w.getContentPane().getWidth());
                System.out.println("WINDOW: " + e.getComponent().getHeight() + "    " + e.getComponent().getWidth());
            }
        });
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}
