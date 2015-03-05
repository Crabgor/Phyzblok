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
    // region Constants
    public static final int MODEL_VIEW_RATIO = 8;
    // endregion

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
        //mainShape.updatePosition();
        //for (PhyzRectangle pR : dynamics)
        //    pR.updatePosition();
        dynamicsPanel.updateBodies();
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

        PhyzRectangle mainShape = psf.MakeRectangle(mainEntity);
        List<PhyzRectangle> dynamics =  psf.MakeRectangles(dynamicEntities);
        List<PhyzRectangle> staticRects = psf.MakeRectangles(staticEntities);
        //TODO: mainShape, dynamics, staticRects need to be made accessable
        createGUI();

        dynamicsPanel.AddShape(mainShape);
        dynamicsPanel.AddShapes(dynamics);
        dynamicsPanel.revalidate();
        dynamicsPanel.repaint();

        staticsPanel.AddShapes(staticRects);
        staticsPanel.revalidate();
        staticsPanel.repaint();

        window.revalidate();
        window.repaint();


        return true;    // TODO: Ensure successful method completion
    }


    private /*static*/ void createGUI()
    {
        window = new GameWindow("PhyzBlok");
        window.setLayout(null);
       // window.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        dynamicsPanel = new EntityPanel(controller.getModel().getMainEntity().getColour());
        dynamicsPanel.setSize(1000, 1000);
        //dynamicsPanel.setVisible(true);
        //dynamicsPanel.setBackground(Color.PINK);
        dynamicsPanel.setOpaque(false);
        dynamicsPanel.setLocation(0,0);
        //pack();
        //staticsPanel = new EntityPanel();
        //staticsPanel.setSize(1000, 1000);

        //staticsPanel.setVisible(true);
        //staticsPanel.setBackground(Color.BLUE);
        staticsPanel = new EntityPanel(Color.BLUE);
        staticsPanel.setSize(1000, 1000);
        staticsPanel.setOpaque(false);
        // TODO: Currently panels fill window, fix this if necessary
        window.getContentPane().add(staticsPanel);
        window.getContentPane().add(dynamicsPanel);


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