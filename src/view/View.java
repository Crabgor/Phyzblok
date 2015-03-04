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

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class View
{
    // region Fields
    Controller controller;
    JPanel staticsPanel;
    JPanel dynamicsPanel;

    PhyzRectangle mainShape;
    List<PhyzRectangle> dynamics;
    // endregion

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
        // TODO: Finish implementation of View.buildLevelFromModel
        throw new NotImplementedException();
    }
}