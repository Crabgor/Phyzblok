package view;

import controller.Controller;
import entities.ViewEntity;
import model.Model;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Created by Cregnacht on 2015-03-03.
 */
public class View //extends JPanel
{
    //private static final int PREF_W = 700;
   // private static final int PREF_H = PREF_W;
    private GameWindow window;
    // region Fields
    Controller controller;
    JPanel staticsPanel;
    JPanel dynamicsPanel;

    PhyzRectangle mainShape;
    List<PhyzRectangle> dynamics;
    // endregion
/*
    public View(GameWindow frame) {
        this.window = frame;
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);
    }
*/



    // region Getters
    public Controller getController()
    {
        return controller;
    }
    // endregion

    // region Setters
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
    // endregion

    public View() {}

    public View(Controller controller)
    {
        this.controller = controller;
    }


    /**
     *
     */
    public void update()
    {
        mainShape.updatePosition();
        for (PhyzRectangle pR : dynamics)
            pR.updatePosition();
        updateGraphics();
    }


    /**
     *
     */
    private void updateGraphics()
    {
        dynamicsPanel.revalidate();
        dynamicsPanel.repaint();
    }


    /**
     *
     * @return Returns the keycode for the selected input.
     */
    public int pollInput()
    {
        int keycode = 0x00;
        // TODO: Finish implementation of View.pollInput
        return keycode;
    }


    public boolean buildLevelFromModel()
    {
        controller.getModel().getMainEntity();
        controller.getModel().getDynamicEntities();
        // TODO: Finish implementation of View.buildLevelFromModel
        throw new NotImplementedException();
    }

//window
    private static void createAndShowGui() {

        GameWindow frame = new GameWindow("Game_Window");
        add(panel, BorderLayout.CENTER)
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}