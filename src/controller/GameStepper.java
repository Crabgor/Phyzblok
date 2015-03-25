package controller;

import enums.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cregnacht on 2015-03-24.
 */
public class GameStepper implements ActionListener
{
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Controller controller = Controller.getInstance();

        if (controller.getState() == GameState.PLAY)
        {
            controller.getInputs();
            controller.updateModel();
            controller.renderView();
        }

        else controller.stopTimer();
    }
}
