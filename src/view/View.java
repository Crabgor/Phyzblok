package view;

import controller.Controller;
import entities.ViewEntity;
import enums.GameState;
import interfaces.IModelView;
import model.Model;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class View implements IModelView
{
    // region Fields
    private /*static*/ GameWindow window;
    private Controller controller;
    private /*static*/ EntityPanel staticsPanel;
    private /*static*/ EntityPanel dynamicsPanel;

    private PhyzRectangle mainShape;
    private List<PhyzRectangle> dynamics;

    private InputListener inputListener = InputListener.getInstance();
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
        return inputListener.getKeycode();
    }


    public boolean buildLevelFromModel()
    {
        ViewEntity mainEntity = controller.getModel().getMainEntity();
        List<ViewEntity> staticEntities = controller.getModel().getStaticEntities();
        List<ViewEntity> dynamicEntities = controller.getModel().getDynamicEntities();
        PhyzShapeFactory psf = new PhyzShapeFactory();

        mainShape = psf.MakeRectangle(mainEntity);
        dynamics =  psf.MakeRectangles(dynamicEntities);
        List<PhyzRectangle> staticRects = psf.MakeRectangles(staticEntities);

        createGUI();

        dynamicsPanel.AddShape(mainShape);
        dynamicsPanel.AddShapes(dynamics);
        staticsPanel.AddShapes(staticRects);

        window.revalidate();
        window.repaint();

        return true;    // TODO: Ensure successful method completion
    }


    private /*static*/ void createGUI()
    {
        window = new GameWindow("PhyzBlok");
        dynamicsPanel = new EntityPanel();
        dynamicsPanel.setSize(300, 50);
        //dynamicsPanel.setVisible(true);
        dynamicsPanel.setBackground(Color.BLACK);
        staticsPanel = new EntityPanel();
        staticsPanel.setSize(300, 50);
        //staticsPanel.setVisible(true);
        staticsPanel.setBackground(Color.BLUE);
        // TODO: Currently panels fill window, fix this if necessary
        window.add(dynamicsPanel);
        window.add(staticsPanel);

        window.addKeyListener(inputListener);
    }


    @Override
    public void changeState(GameState state)
    {
        switch (state)
        {
            case EXITING:
                break;
            case LEVEL_SELECT:
                break;
            case LOADING:
                break;
            case MAIN_MENU:
                break;
            case PAUSE:
                break;
            case PLAY:
                dynamicsPanel.setVisible(true);
                staticsPanel.setVisible(true);
                break;
            default:
                break;
        }
    }

/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }*/
}