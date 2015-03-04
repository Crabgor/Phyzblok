package view;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class EntityPanel extends JPanel{


    public EntityPanel() {
        rectangles = new ArrayList<PhyzRectangle>();
        //his.gameTwo = frame;
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);

        }
    private static final int PREF_W = 500;
    private static final int PREF_H = PREF_W;
    private ArrayList<PhyzRectangle> rectangles;

    public void AddShape(PhyzRectangle rectangle)
    {
        rectangles.add(rectangle);
    }


    public void AddShapes(List<PhyzRectangle> shapes)
    {
        for (PhyzRectangle sh : shapes)
            rectangles.add(sh);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (PhyzRectangle rect : rectangles) {
            g2.draw(rect);
        }
    }


}
