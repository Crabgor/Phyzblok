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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class View implements IModelView
{
    // region Constants
    public static float MODEL_VIEW_X = 8;
    public static float MODEL_VIEW_Y = 8;

    private final int  DEFAULT_GOAL_X = 640,
                       DEFAULT_GOAL_Y = 550,
                       DEFAULT_GOAL_W = 240,
                       DEFAULT_GOAL_H = 200,
                       DEFAULT_MODEL_VIEW_X = 8,
                       DEFAULT_MODEL_VIEW_Y = 8;
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
                   levelEndText;



    private MainMenuPanel mainMenuPanel;
    private LevelSelectPanel levelSelectPanel;
    private PausePanel pausePanel;

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
        mainMenuPanel.setSize(window.getSize());
        mainMenuPanel.setVisible(true);
        mainMenuPanel.setBackground(Color.DARK_GRAY);

        pausePanel = new PausePanel();
        //pausePanel.setVisible(true);
        pausePanel.setBackground(Color.pink);

        levelSelectPanel = new LevelSelectPanel();
        levelSelectPanel.setSize(window.getSize());
        levelSelectPanel.setVisible(false);
        levelSelectPanel.setBackground(Color.CYAN);

        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.DARK_GRAY);
        backgroundPanel.setSize(window.getSize());
        backgroundPanel.setOpaque(true);
        backgroundPanel.setLocation(0, 0);

        window.add(mainMenuPanel);
        window.add(levelSelectPanel);
        window.add(pausePanel);

        window.addKeyListener(inputListener);

        window.revalidate();
        window.repaint();

        window.addComponentListener(new ComponentAdapter()
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
                mainMenuPanel.setSize(window.getSize());
                levelSelectPanel.setSize(window.getSize());
            }
        });
    }


    /**
     *
     */
    public void update()
    {
        MODEL_VIEW_X = window.getModelScaleX();
        MODEL_VIEW_Y = window.getModelScaleY();

        if (dynamicsPanel == null) return;

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
    }

    /**
     *
     */
    private void updateGraphics()
    {
        Dimension d = window.getSize();

        if (backgroundPanel.getSize() != d)
        {
            dynamicsPanel.setSize(d);
            mainShapePanel.setSize(d);

            backgroundPanel.setSize(d);
            //backgroundPanel.revalidate();
            backgroundPanel.repaint();

            staticsPanel.setSize(d);
            //staticsPanel.revalidate();
            staticsPanel.repaint();

            textPanel.setSize(d);
            //textPanel.revalidate();
            textPanel.repaint();

            goalPanel.setLocation((int) (DEFAULT_GOAL_X * MODEL_VIEW_X / DEFAULT_MODEL_VIEW_X),
                                  (int) (DEFAULT_GOAL_Y * MODEL_VIEW_Y / DEFAULT_MODEL_VIEW_Y));
            goalPanel.setSize((int) (DEFAULT_GOAL_W * MODEL_VIEW_X / DEFAULT_MODEL_VIEW_X),
                              (int) (DEFAULT_GOAL_H * MODEL_VIEW_Y / DEFAULT_MODEL_VIEW_Y));
        }

        //dynamicsPanel.revalidate();
        dynamicsPanel.repaint();
        //mainShapePanel.revalidate();
        mainShapePanel.repaint();

        //if (controller.getModel().getMainEntity().getX()>650 &&
        //        controller.getModel().getMainEntity().getY()> 550 &&
        //        controller.getModel().getMainEntity().getY()< (650+235) &&
        //        controller.getModel().getMainEntity().getY()< (550+200) &&
        //        goalPanel.getBackground()!=Color.PINK)
        if (goalPanel.getBounds().contains(controller.getModel().getMainEntity().getX(), controller.getModel().getMainEntity().getY()))
            finishLevel(); // TODO: Require user input for this.
    }

    private void finishLevel()
    {
        goalPanel.setBackground(Color.PINK);
        levelEndText = new JLabel("YOU WIN");
        levelEndText.setText("<html>YOU WIN  <br> press space to continue</html>");
        levelEndText.setFont(new Font("Verdana", 1, 20));
        levelEndText.setForeground(Color.GREEN);
        levelEndText.setLocation(200, 200);
        textPanel.add(levelEndText);
        //textPanel.revalidate();
        textPanel.repaint();
        Controller.getInstance().setState(GameState.LEVEL_SELECT);
        //TODO: controller update. press space bar to go to menu
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

        createGUI();

        dynamicsPanel.AddShapes(dynamics);
        dynamicsPanel.add(gameInstructions);
        dynamicsPanel.add(clickCountNumber);
        staticsPanel.AddShapes(staticRects);
        mainShapePanel.AddShape(mainShape);
        textPanel.add(levelEndText);

        //window.revalidate();
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

        goalPanel = new EntityPanel();
        goalPanel.setBackground(new Color(50, 130, 20));
        goalPanel.setLocation((int) (DEFAULT_GOAL_X * MODEL_VIEW_X / DEFAULT_MODEL_VIEW_X),
                              (int) (DEFAULT_GOAL_Y * MODEL_VIEW_Y / DEFAULT_MODEL_VIEW_Y));
        goalPanel.setSize((int) (DEFAULT_GOAL_W * MODEL_VIEW_X / DEFAULT_MODEL_VIEW_X),
                          (int) (DEFAULT_GOAL_H * MODEL_VIEW_Y / DEFAULT_MODEL_VIEW_Y));
        goalPanel.setOpaque(true);

        levelEndText = new JLabel();
        levelEndText.setFont(new Font("Verdana",1,30));
        levelEndText.setForeground(Color.GREEN);
        levelEndText.setLocation(10, 20);

        textPanel = new EntityPanel();
        textPanel.setFont(new Font("Verdana",1, 50));
        textPanel.setForeground(Color.GREEN);
        textPanel.setVisible(true);
        textPanel.setOpaque(false);
        textPanel.setSize(window.getSize());
        textPanel.setLocation(0,300);

        mainShapePanel = new EntityPanel();
        mainShapePanel.setSize(window.getSize());
        mainShapePanel.setOpaque(false);
        mainShapePanel.setLocation(0, 0);
        mainShapePanel.setVisible(true);

        dynamicsPanel = new EntityPanel();
        dynamicsPanel.setSize(window.getSize());
        dynamicsPanel.setOpaque(false);
        dynamicsPanel.setLocation(0,0);

        staticsPanel = new EntityPanel();
        staticsPanel.setSize(window.getSize());
        staticsPanel.setOpaque(false);

        window.add(textPanel);
        window.add(mainShapePanel);
        window.add(staticsPanel);
        window.add(dynamicsPanel);
        window.add(goalPanel);
        window.add(backgroundPanel);
        //window.revalidate();
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
                levelSelectPanel.setVisible(true);
                if (mainShapePanel != null)
                {
                    mainShapePanel.clearBodies();
                    window.remove(mainShapePanel);
                    mainShapePanel = null;
                    staticsPanel.clearBodies();
                    window.remove(staticsPanel);
                    staticsPanel = null;
                    dynamicsPanel.clearBodies();
                    window.remove(dynamicsPanel);
                    dynamicsPanel = null;
                    goalPanel.clearBodies();
                    window.remove(goalPanel);
                    goalPanel = null;
                    textPanel.remove(levelEndText);
                    textPanel.remove(gameInstructions);
                    textPanel.remove(clickCountNumber);
                    window.remove(textPanel);
                    textPanel = null;
                    window.remove(backgroundPanel);
                }
                break;
            case LOADING:
                levelSelectPanel.setVisible(false);
                controller.resetKeyCount();
                break;
            case MAIN_MENU:
                levelSelectPanel.setVisible(false);
                mainMenuPanel.setVisible(true);
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