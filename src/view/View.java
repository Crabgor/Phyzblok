package view;

import controller.Controller;
import entities.ViewEntity;
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
public class View //extends JPanel
{
    //private static final int PREF_W = 700;
   // private static final int PREF_H = PREF_W;
    private static GameWindow window;
    private static EntityPanel Panel;
    // region Fields
    private Controller controller;
    private static EntityPanel staticsPanel;
    private static EntityPanel dynamicsPanel;

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
        ViewEntity mainShape = controller.getModel().getMainEntity();
        List<ViewEntity> staticEntities = controller.getModel().getStaticEntities();
        List<ViewEntity> dynamicEntities = controller.getModel().getDynamicEntities();
        PhyzShapeFactory psf = new PhyzShapeFactory();

        PhyzRectangle mainRectangle = psf.MakeRectangle(mainShape);
        List<PhyzRectangle> staticRects = psf.MakeRectangles(staticEntities);
        List<PhyzRectangle> dynamicRects =  psf.MakeRectangles(dynamicEntities);


        createGUI();

        dynamicsPanel.AddShape(mainRectangle);
        staticsPanel.AddShapes(staticRects);
        dynamicsPanel.AddShapes(dynamicRects);

        window.revalidate();
        window.repaint();

        // TODO: Finish implementation of View.buildLevelFromModel
        throw new NotImplementedException();
    }


//window
    private static void createGUI()
    {
        window = new GameWindow("PhyzBlok");

<<<<<<< Updated upstream
        dynamicsPanel = new EntityPanel();
        dynamicsPanel.setSize(300, 50);
        dynamicsPanel.setVisible(true);
        staticsPanel = new EntityPanel();
        staticsPanel.setSize(300, 50);
        staticsPanel.setVisible(true);
        // TODO: Currently panels fill window, fix this if necessary
        window.add(dynamicsPanel);
        window.add(staticsPanel);
        //window.revalidate();
        //window.repaint();
=======
        GameWindow frame = new GameWindow("Game_Window");
        //add(panel, BorderLayout.CENTER)
    }
>>>>>>> Stashed changes


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