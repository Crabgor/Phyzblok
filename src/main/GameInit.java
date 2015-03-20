package main;

import controller.*;
import entities.Entity;
import entities.MainEntity;
import entities.StdEntity;
import entities.WallEntity;
import enums.GameState;
import levelGeneration.Level;
import model.Model;
import org.jbox2d.common.Vec2;
import view.View;

import java.awt.*;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class GameInit
{
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        model.setController(controller);
        view.setController(controller);


        // region Testing Level
        //MainEntity mainEntity = new MainEntity(5.0f, 9.0f, -10.0f, 8.0f, 8.0f);
        //Entity[] entities = {
        //       // new WallEntity(10.0f, 0.0f, 20.0f, 10.0f),
        //        //new WallEntity(15.0f, -10.0f, 10.0f, 10.0f),
        //       // new WallEntity(4.0f, -9.0f, 3.0f, 200.0f),
        //        new WallEntity(50.0f, -3.0f, 100.0f, 6.0f),
        //       // new WallEntity(90.5f, -1.50f, 3.0f, 200.0f),
        //        new WallEntity(50.0f, -53.0f, 100.0f, 6.0f),
        //
        //       // new WallEntity(50.0f, -51.5f, 100.0f, 3.0f),
        //        //new WallEntity(50.0f, -101.5f, 100.0f, 3.0f),
        //
        //
        //        //new WallEntity(10.0f, 0.0f, 20.0f, 40.0f), //apparing
        //
        //        //new WallEntity(15.0f, -10.0f, 10.0f, 30.0f),
        //        new StdEntity(10.0f, Color.GREEN, new Rectangle(150, 2, 7, 7), 150.0f, -2.0f, 7.0f, 7.0f, 0.0f, 0.000f, 0.5f, 1.0f, 0.0f, 0.0f), // TODO: Rectangle is probably broken here
        //        new StdEntity(10.0f, Color.GREEN, new Rectangle(40, 40, 7, 7), 10.0f, -5.0f, 7.0f, 7.0f, 0.0f, 0.000f, 0.5f, 1.0f, 0.0f, 0.0f),
        //        new StdEntity(10.0f, Color.GREEN, new Rectangle(10, 100, 7, 7), 10.0f, -100.0f, 7.0f, 7.0f, 0.0f, 0.000f, 0.5f, 1.0f, 0.0f, 0.0f)
        //};
        // endregion

        // region Brypo's Level
        MainEntity mainEntity = new MainEntity(5.0f, 5.0f, -20.0f, 8.0f, 8.0f);
        Entity[] entities = {
                new WallEntity(15.0f, -75.0f, 30.0f, 70.0f),
                new WallEntity(95.0f, -55.0f, 30.0f, 30.0f),
                new WallEntity(95.0f, -95f, 30.0f, 30.0f),
                new WallEntity(100.0f, -40.0f, 2.0f, 10.0f),
                //new StdEntity(5f, new Color(200, 0, 255), new Rectangle(10, 5, 10, 3), 10.0f, -5.0f, 10.0f, 2.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f),
                new StdEntity(10.0f, new Color(0, 35, 35), new Rectangle(27, 40, 15, 10), 27.0f, -40.0f, 15.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.000f, 0.0f, 10000.0f),
                new StdEntity(10.0f, new Color(240, 240, 0), new Rectangle(82, 25, 5, 5), 82.0f, -25.0f, 5.0f, 5.0f, 0.0f, 0.0f, 0.0f, 0.000f, 0.0f, 10000.0f)
        };
        // endregion

        float[] goal = { 100.0f, -100.0f };

        Level level = new Level(mainEntity, entities, goal, new Vec2(0.0f, -10.0f), 10);

        if (controller.loadLevel(level))
        {
            controller.setState(GameState.PLAY);
            Game.getInstance(controller).play();
        }
    }
}