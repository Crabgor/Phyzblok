package main;

import controller.Controller;
import enums.GameState;
import levelGeneration.Level;
import model.Model;
import view.View;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class GameInit
{
    /**
     *
     * @param args
     */
    public void main(String[] args)
    {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        model.setController(controller);
        view.setController(controller);

        // TODO: HARD-CODED LEVEL
        Level level = null; // new Level();

        if (controller.loadLevel(level))
        {
            controller.setState(GameState.PLAY);
            new controller.Game().play();
        }
    }
}
