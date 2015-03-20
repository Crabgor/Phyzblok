package view;

import controller.Controller;
import entities.ViewEntity;
import enums.GameState;
import interfaces.IModelView;

import javax.swing.*;
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
    private JLabel clickCountNumber,
                    gameInstructions;


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
        dynamicsPanel.updateBodies();
        staticsPanel.updateBodies();
        mainShapePanel.updateBodies();

        updateGraphics();
    }

    /**
     *updates the displayed number
     */
    public void updateNumText(int numUsed, int numTotal)
    {
        int numLeft = numTotal-numUsed;
        clickCountNumber.setText("you have " + String.valueOf(numLeft) + " clicks left");
        gameInstructions.setText(
                "Main ro ="+ String.valueOf(controller.getModel().getMainEntity().getAngle()) +
                "  Black ro="+ String.valueOf(controller.getModel().getDynamicEntities().get(0).getAngle()) +
                " Yel ro=" + String.valueOf(controller.getModel().getDynamicEntities().get(1).getAngle())
        );
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
        if(controller.getModel().getMainEntity().getX()>650 && controller.getModel().getMainEntity().getY()> 550 ){
            goalPanel.setBackground(Color.PINK);
        }
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
        dynamicsPanel.add(gameInstructions);
        dynamicsPanel.add(clickCountNumber);
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
        gameInstructions = new JLabel("In this Game you must use the arrow keys to change how gravity acts on the main shape");
        gameInstructions.setFont(new Font("Verdana",1,15));
        gameInstructions.setForeground(Color.PINK);

        clickCountNumber = new JLabel("you have "+ String.valueOf(controller.getMaxKeyCount()) + " clicks.  Good Luck");
        clickCountNumber.setFont(new Font("Verdana",1,20));
        clickCountNumber.setForeground(Color.PINK);
        clickCountNumber.setLocation(20,20);

        window = new GameWindow("PhyzBlok");
        window.setLayout(null);

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
}