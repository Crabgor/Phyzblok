package view;

import controller.Controller;
import entities.ViewEntity;
import enums.GameState;
import interfaces.IModelView;
import view.controls.LevelSelectPanel;
import view.controls.MainMenuPanel;
import view.controls.PausePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class View implements IModelView
{
    // region Constants
    public static int MODEL_VIEW_RATIO = 8;
    public static float MODEL_VIEW_X = 8;
    public static float MODEL_VIEW_Y = 8;
    // endregion

    // region Fields
    private GameWindow window;
    private Controller controller;
    private EntityPanel staticsPanel,
                        dynamicsPanel,
                        goalPanel,
                        mainShapePanel,
                        textPanel;

    private JPanel backgroundPanel;


    private JLabel clickCountNumber,
                    gameInstructions,
                    LevelEndText;



    private MainMenuPanel mainMenuPanel;
    private LevelSelectPanel levelSelectPanel;
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
        window = GameWindow.getInstance();
        window.setLayout(null);


        mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.setVisible(true);
        mainMenuPanel.setBackground(Color.DARK_GRAY);

        pausePanel = new PausePanel();
        //pausePanel.setVisible(true);
        pausePanel.setBackground(Color.pink);

        levelSelectPanel = new LevelSelectPanel();
       // if(mainMenuPanel.getvis){            levelSelectPanel.setVisible(true);}
        levelSelectPanel.setBackground(Color.CYAN);

        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.DARK_GRAY);
        backgroundPanel.setSize(1000, 1000);
        backgroundPanel.setOpaque(true);
        backgroundPanel.setLocation(0, 0);

        window.add(mainMenuPanel);
        window.add(levelSelectPanel);
        window.add(pausePanel);
        window.addKeyListener(inputListener);

        window.revalidate();
        window.repaint();
    }


    /**
     *
     */
    public void update()
    {
        MODEL_VIEW_X = window.getModelScaleX();
        MODEL_VIEW_Y = window.getModelScaleY();

        dynamicsPanel.updateBodies();
        staticsPanel.updateBodies();
        mainShapePanel.updateBodies();
        textPanel.updateBodies();


        updateGraphics();
    }

    /**
     *updates the displayed number
     */
    public void updateNumText(int numUsed, int numTotal)
    {
        int numLeft = numTotal-numUsed;
        clickCountNumber.setText("you have " + String.valueOf(numLeft) + " clicks left");
        gameInstructions.setText("\n");
        //        "Main ro ="+ String.valueOf(controller.getModel().getMainEntity().getAngle()) +
        //        "  Black ro="+ String.valueOf(controller.getModel().getDynamicEntities().get(0).getAngle()) +
        //        " Yel ro=" + String.valueOf(controller.getModel().getDynamicEntities().get(1).getAngle())
        //);
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
        textPanel.revalidate();
        textPanel.repaint();
        if(controller.getModel().getMainEntity().getX()>650 &&
                controller.getModel().getMainEntity().getY()> 550 &&
                controller.getModel().getMainEntity().getY()< (650+235) &&
                controller.getModel().getMainEntity().getY()< (550+200) &&
                goalPanel.getBackground()!=Color.PINK){
            finishLevel();
        }
    }

    private void finishLevel()
    {


        goalPanel.setBackground(Color.PINK);
        LevelEndText = new JLabel("YOU WIN");
        LevelEndText.setText("<html>YOU WIN  <br> press space to continue</html>");
        LevelEndText.setFont(new Font("Verdana", 1, 20));
        LevelEndText.setForeground(Color.GREEN);
        LevelEndText.setLocation(200, 200);
        Controller.getInstance().setState(GameState.LEVEL_SELECT);
        textPanel.add(LevelEndText);
        textPanel.revalidate();
        textPanel.repaint();


        //TODO: controller update. press space bar to go to menue
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

        textPanel.add(LevelEndText);
        textPanel.revalidate();
        textPanel.repaint();

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

        goalPanel = new EntityPanel(Color.GREEN);
        goalPanel.setBackground(new Color(51, 133, 19));
        goalPanel.setSize(235, 200);
        goalPanel.setOpaque(true);
        goalPanel.setLocation(640, 550);

        LevelEndText = new JLabel();
        LevelEndText.setFont(new Font("Verdana",1,30));
        LevelEndText.setForeground(Color.GREEN);
        LevelEndText.setLocation(10,20);


        textPanel = new EntityPanel(new Color(200, 164, 245));
        textPanel.setFont(new Font("Verdana",1, 50));
        textPanel.setForeground(Color.GREEN);
        //textPanel.setBackground(Color.YELLOW);
        textPanel.setVisible(true);
        textPanel.setOpaque(false);
        textPanel.setSize(1000,1000);
        textPanel.setLocation(0,300);




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

        window.add(textPanel);
        window.add(mainShapePanel);
        window.add(staticsPanel);
        window.add(dynamicsPanel);
        window.add(goalPanel);
        window.add(backgroundPanel);
        window.revalidate();
        window.repaint();
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
                mainMenuPanel.setVisible(false);
                //window.add(levelSelectPanel);
                levelSelectPanel.setVisible(true);
                if(mainShapePanel!=null)
                {

                    //window.getContentPane().remove(dynamicsPanel);
                    //dynamicsPanel.remove(0);
                    dynamicsPanel.removeAll();
                    //dynamicsPanel.remove(1);
                    window.remove(mainShapePanel);
                    window.remove(dynamicsPanel);
                    //ToDo: remove palel shapes propperly

                }
                break;
            case LOADING:
                levelSelectPanel.setVisible(false);
                window.remove(mainMenuPanel);
                controller.resertKeyCount();
                break;
            case MAIN_MENU:
                window.add(mainMenuPanel);
                levelSelectPanel.setVisible(false);
                break;
            case PAUSE:
                window.add(pausePanel);
                pausePanel.setOpaque(true);
                window.revalidate();
                window.repaint();


                break;
            case PLAY:
                pausePanel.setOpaque(false);
                window.remove(pausePanel);
                //window.add(textPanel);
               // window.add(dynamicsPanel);
               // window.add(backgroundPanel);
                //window.add(dynamicsPanel);

                textPanel.setVisible(true);
                mainShapePanel.setVisible(true);
                dynamicsPanel.setVisible(true);
                staticsPanel.setVisible(true);
                goalPanel.setVisible(true);
                break;
            default:
                break;
        }

        window.revalidate();
        window.repaint();
    }
}