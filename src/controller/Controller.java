package controller;

import enums.GameState;
import levelGeneration.Level;
import main.GameInit;
import model.Model;
import view.View;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Cregnacht on 2015-02-26.
 */
public class Controller
{
    private static Controller singleton;
    public static Controller getInstance()
    {
        if (singleton == null)
            singleton = new Controller();
        return singleton;
    }

    // region Constants
    public final float STEP_PERIOD = 1 / 30.0f; // Time in seconds.
    public final int VELOCITY_ITERATIONS_PER_STEP = 100;
    public final int POSITION_ITERATIONS_PER_STEP = 300;

    public final GameState INITIAL_STATE = GameState.MAIN_MENU;
    // endregion

    // region Supporting Objects
    private Model model;
    private View view;
    private Timer gameTimer;
    // endregion

    // region Fields
    private GameState state;
    private int gravMagnitude;
    private int gravX, gravY;
    private int previousKeycode = -0x01;
    private int keyCount = 0;
    private int maxKeyCount = 0;
    private int currentLevel = -1;
    // endregion


    // region Getters/Setters
    public Model getModel()
    {
        return model;
    }

    public void setModel(Model m)
    {
        model = m;
    }

    public void setView(View v)
    {
        view = v;
    }

    public synchronized GameState getState()
    {
        return state;
    }

    public void setState(GameState state)
    {
        if (this.state == state) return;
        this.state = state;
        notifyModelViewStateChange();
        if (this.state == GameState.PLAY)
            //Game.getInstance().play();
            startTimer();
    }


    public void setCurrentLevel(int index)
    {
        currentLevel = index;
    }
    // endregion


    /**
     *
     */
    private Controller()
    {
        this.state = INITIAL_STATE;
        this.gameTimer = new Timer((int) (STEP_PERIOD * 1000), new GameStepper());
    }


    /**
     *
     * @param model
     */
    protected Controller(Model model, View view)
    {
        this();
        this.model = model;
        this.view = view;
    }


    public void startTimer()
    {
        gameTimer.start();
    }


    public void stopTimer()
    {
        gameTimer.stop();
    }


    public boolean loadLevel(int i)
    {
        Level l = GameInit.getLevel(i);
        if (l == null) return false;
        return loadLevel(l);
    }


    public boolean loadLevel(Level level)
    {
        if (model.buildLevel(level))
        {
            maxKeyCount = level.getMaxKeyCount();
            if (view.buildLevelFromModel())
                return true;
        }
        return false;
    }


    public void startCurrentLevel()
    {
        startGame(currentLevel);
    }


    public void startGame(int i)
    {
        if (loadLevel(i))
        {
            setState(GameState.PLAY);
        }
    }


    public void startGame(Level level)
    {
        if (loadLevel(level))
        {
            setState(GameState.PLAY);
        }
    }


    /**
     *
     */
    public void updateModel()
    {
        model.stepWorld(STEP_PERIOD, VELOCITY_ITERATIONS_PER_STEP, POSITION_ITERATIONS_PER_STEP);
    }


    /**
     *
     */
    public void renderView()
    {
        view.update();
    }


    /**
     *
     */
    private void notifyModelViewStateChange()
    {
        model.changeState(state);
        view.changeState(state);
    }


    /**
     *
     */
    public void getInputs()
    {
        int keycode = view.pollInput();
        if(keycode == 32) //space bar
        {
            getInstance().setState(GameState.LEVEL_SELECT);
            keyCount =0;
        }

        if(keycode == 82) //r
        {
            setState(GameState.LEVEL_SELECT);
            setState(GameState.LOADING);
            setCurrentLevel(currentLevel);
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    keyCount = 0;
                    startCurrentLevel();
                }
            });
        }
        if(keycode == 80 && !getInstance().getState().equals(GameState.PAUSE)) // p
        {
            getInstance().setState(GameState.PAUSE);
        }
        else if(keycode == 80 && getInstance().getState().equals(GameState.PAUSE))
        {
            getInstance().setState(GameState.PLAY);
        }

        if (keyCount >= maxKeyCount) return;


        if (keycode != previousKeycode)
        {
            previousKeycode = keycode;
            applyUserInput(previousKeycode);
            if (keycode <= 40 && keycode >= 37) //arrow key key codes
            {
                keyCount++;
                view.updateNumText(keyCount, maxKeyCount);
            }
        }
    }


    public void resetKeyCount()
    {
        keyCount=0;
    }
    /**
     *
     */
    public int getMaxKeyCount()
    {
        return maxKeyCount;
    }

    /**
     *
     * @param keyCode
     */
    private void applyUserInput(int keyCode)
    {
        switch (keyCode)
        {
            // region DOWN
            case KeyEvent.VK_DOWN:
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
                        gravX = 0;
                        gravY = -1;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region LEFT
            case KeyEvent.VK_LEFT:
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
                        gravX = -1;
                        gravY = 0;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region RIGHT
            case KeyEvent.VK_RIGHT:
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
                        gravX = 1;
                        gravY = 0;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region UP
            case KeyEvent.VK_UP:
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
                        gravX = 0;
                        gravY = 1;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 0
            case KeyEvent.VK_0:
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
                        gravMagnitude = 0;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 1
            case KeyEvent.VK_1:
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
                        gravMagnitude = 1;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 2
            case KeyEvent.VK_2:
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
                        gravMagnitude = 2;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 3
            case KeyEvent.VK_3:
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
                        gravMagnitude = 3;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 4
            case KeyEvent.VK_4:
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
                        gravMagnitude = 4;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 5
            case KeyEvent.VK_5:
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
                        gravMagnitude = 5;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 6
            case KeyEvent.VK_6:
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
                        gravMagnitude = 6;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 7
            case KeyEvent.VK_7:
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
                        gravMagnitude = 7;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 8
            case KeyEvent.VK_8:
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
                        gravMagnitude = 8;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion

            // region 9
            case KeyEvent.VK_9:
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
                        gravMagnitude = 9;
                        model.applyPlayerGravity(gravX, gravY, gravMagnitude);
                        break;

                    default:
                        break;
                }
                break;
            // endregion
            default:
                break;
        }
    }
}
