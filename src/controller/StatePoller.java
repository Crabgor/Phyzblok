package controller;

import enums.GameState;

/**
 * Created by Cregnacht on 2015-03-23.
 */
public class StatePoller extends Thread
{
    public void run()
    {
        Controller controller = Controller.getInstance();


        double previousTime = System.currentTimeMillis(); // TODO: Ensure this doesn't cause any problems.

        while (true)
        {
            double currentTime = System.currentTimeMillis();
            double elapsedTime = currentTime - previousTime;

            if (elapsedTime <= 5000) continue;

            previousTime = currentTime;

            if (controller.getState() == GameState.LOADING && !Game.getInstance().isRunning())
            {
                controller.startCurrentLevel();
            }
        }
    }
}
