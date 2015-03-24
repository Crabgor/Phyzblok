package view;

import controller.Controller;
import entities.ViewEntity;
import enums.GameState;
import interfaces.IModelView;
import view.controls.MainMenuPanel;
import view.controls.PausePanel;

import java.awt.*;
import java.util.List;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class View implements IModelView
{
    // region Constants
    public static final int MODEL_VIEW_RATIO = 8;
    // endregion

    // region Fields
    private GameWindow window;
    private Controller controller;
    private EntityPanel staticsPanel,
                        dynamicsPanel,
                        goalPanel,
                        backGroundPanel,
                        mainShapePanel;
    private MainMenuPanel mainMenuPanel;
    private PausePanel pausePanel;


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

    public View(Controller c)
    {
        controller = c;
        window = new GameWindow("PhyzBlok");
        window.setLayout(null);

        mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.setVisible(true);

        window.add(mainMenuPanel);
        window.revalidate();
        window.repaint();
    }


    /**
     *
     */
    public void update()
    {
        dynamicsPanel.updateBodies();
        staticsPanel.updateBodies();
        mainShapePanel.updateBodies();
        updateGraphics();
    }


    /**
     *
     */
    private void updateGraphics()
    {
        dynamicsPanel.revalidate();
        dynamicsPanel.repaint();
        mainShapePanel.revalidate();
        mainShapePanel.repaint();
    }


    /**
     *
     * @return Returns the keycode for the selected input.
     */
    public int pollInput()
    {
        return inputListener.getKeycode();
    }


    /**
     *
     * @return
     */
    public boolean buildLevelFromModel()
    {
        ViewEntity mainEntity = controller.getModel().getMainEntity();
        List<ViewEntity> staticEntities = controller.getModel().getStaticEntities();
        List<ViewEntity> dynamicEntities = controller.getModel().getDynamicEntities();
        PhyzShapeFactory psf = new PhyzShapeFactory();

        PhyzRectangle mainShape = psf.MakeRectangle(mainEntity);
        List<PhyzRectangle> dynamics =  psf.MakeRectangles(dynamicEntities);
        List<PhyzRectangle> staticRects = psf.MakeRectangles(staticEntities);
        //TODO: mainShape, dynamics, staticRects need to be made accessible
        createGUI();

        dynamicsPanel.AddShapes(dynamics);
        dynamicsPanel.revalidate();
        dynamicsPanel.repaint();

        staticsPanel.AddShapes(staticRects);
        staticsPanel.revalidate();
        staticsPanel.repaint();


        mainShapePanel.AddShape(mainShape);
        mainShapePanel.revalidate();
        mainShapePanel.repaint();
        if(mainShape.getX() >100 && mainShape.getY() > 100)
        {
            goalPanel.setBackground(Color.black);
        }


        goalPanel.revalidate();
        goalPanel.repaint();

        window.revalidate();
        window.repaint();

        return true;    // TODO: Ensure successful method completion
    }


    /**
     *
     */
    private void createGUI()
    {
        backGroundPanel = new EntityPanel(controller.getModel().getMainEntity().getColour());
        backGroundPanel.setBackground(Color.DARK_GRAY);
        backGroundPanel.setSize(1000, 1000);
        backGroundPanel.setOpaque(true);
        backGroundPanel.setLocation(0, 0);

        goalPanel = new EntityPanel(Color.GREEN);
        goalPanel.setBackground(new Color(51, 133, 19));
        goalPanel.setSize(235, 200);
        goalPanel.setOpaque(true);
        goalPanel.setLocation(640, 550);

        mainShapePanel = new EntityPanel(new Color(51, 164, 245));
        mainShapePanel.setSize(1000, 1000);
        mainShapePanel.setOpaque(false);
        mainShapePanel.setLocation(0, 0);
        mainShapePanel.setVisible(true);

        dynamicsPanel = new EntityPanel(new Color(0, 35, 35));
        dynamicsPanel.setSize(1000, 1000);
        dynamicsPanel.setOpaque(false);
        dynamicsPanel.setLocation(0,0);

        staticsPanel = new EntityPanel(new Color(245, 132, 51));
        staticsPanel.setSize(1000, 1000);
        staticsPanel.setOpaque(false);

        // TODO: Currently panels fill window, fix this if necessary
        // TODO: Panels and inputListener may be added multiple times per game instance over the course of play, fix this
        window.getContentPane().add(mainShapePanel);
        window.getContentPane().add(staticsPanel);
        window.getContentPane().add(dynamicsPanel);
        window.getContentPane().add(goalPanel);
        window.getContentPane().add(backGroundPanel);

        window.addKeyListener(inputListener);
    }


    /**
     *
     * @param state
     */
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
                mainMenuPanel.setVisible(false);
                break;
            case MAIN_MENU:
                mainMenuPanel.setVisible(true);
                break;
            case PAUSE:
                break;
            case PLAY:
                mainShapePanel.setVisible(true);
                dynamicsPanel.setVisible(true);
                staticsPanel.setVisible(true);
                mainMenuPanel.setVisible(false);
                break;
            default:
                break;
        }

        window.repaint();
        window.revalidate();
    }
}