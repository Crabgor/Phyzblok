package controller;

import enums.GameState;

/**
 * Created by Cregnacht on 2015-02-28.
 */
public class Game
{
    private Controller controller;
    private boolean running;

    private static Game singleton;


    public static synchronized Game getInstance()
    {
        if (singleton == null)
            singleton = new Game(Controller.getInstance());
        return singleton;
    }


    protected Game(Controller controller)
    {
        this.controller = controller;
        this.running = false;
    }


    public boolean isRunning()
    {
        return running;
    }


    /**
     *
     */
    public void play()
    {
        double previousTime = System.currentTimeMillis(); // TODO: Ensure this doesn't cause any problems.
        running = true;

        while (controller.getState() == GameState.PLAY)
        {
            double currentTime = System.currentTimeMillis();
            double elapsedTime = currentTime - previousTime;

            if (elapsedTime <= controller.STEP_PERIOD * 1000) continue;

            previousTime = currentTime;

            controller.getInputs();
            controller.updateModel();
            controller.renderView();
        }

        running = false;
    }
}
