package controller;

import enums.GameState;

/**
 * Created by Cregnacht on 2015-02-28.
 */
public class Game
{
    Controller controller;


    /**
     *
     */
    public void play()
    {
        double previousTime = System.currentTimeMillis(); // TODO: Ensure this doesn't cause any problems.
        double lagTime = 0.0;

        while (controller.getState() == GameState.PLAY)
        {
            double currentTime = System.currentTimeMillis();
            double elapsedTime = currentTime - previousTime;
            previousTime = currentTime;
            lagTime += elapsedTime;

            // TODO : PROCESS INPUTS

            for ( ; lagTime >= controller.STEP_PERIOD; lagTime -= controller.STEP_PERIOD)
                controller.updateModel();

            controller.renderView();
        }
    }
}
