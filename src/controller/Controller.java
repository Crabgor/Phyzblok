package controller;

import enums.GameState;
import levelGeneration.Level;
import model.Model;
import view.View;

import javax.naming.ldap.Control;
import java.awt.event.KeyEvent;

/**
 * Created by Cregnacht on 2015-02-26.
 */
public class Controller
{
    // region Constants
    public final float STEP_PERIOD = 1 / 60.0f; // Time in seconds.
    public final int VELOCITY_ITERATIONS_PER_STEP = 1;
    public final int POSITION_ITERATIONS_PER_STEP = 1;

    public final GameState INITIAL_STATE = GameState.LOADING;
    // endregion

    // region Supporting Objects
    private Model model;
    private View view;
    // endregion

    // region Fields
    //VIEW
    private GameState state;
    private int gravMagnitude;
    private int gravX, gravY;
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

    public GameState getState()
    {
        return state;
    }

    public void setState(GameState state)
    {
        this.state = state;
    }
    // endregion


    /**
     *
     */
    private Controller()
    {
        this.state = INITIAL_STATE;
    }


    /**
     *
     * @param model
     */
    public Controller(Model model, View view)
    {
        this();
        this.model = model;
        this.view = view;
    }


    public boolean loadLevel(Level level)
    {
        if (model.buildLevel(level))
            if (view.buildLevelFromModel())
                return true;

        return false;
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
    public void getInputs()
    {
        applyUserInput(view.pollInput());
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

            // TODO: Determine if these values of gravity are enough, or if there should be scaling or a way to increase it further (by a factor G)
            default:
                break;
        }
    }
}
