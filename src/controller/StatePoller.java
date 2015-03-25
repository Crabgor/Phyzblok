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

        while (true)
        {
            try { currentThread().sleep(2000); }
            catch (Exception e) {}

            //double currentTime = System.currentTimeMillis();
            //double elapsedTime = currentTime - previousTime;

            //if (elapsedTime <= 2000) continue;

            //previousTime = currentTime;

            if (controller.getState() == GameState.LOADING && !Game.getInstance().isRunning())
            {
                controller.startCurrentLevel();
            }
        }
    }
}
