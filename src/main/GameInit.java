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

        MainEntity mainEntity = new MainEntity(5.0f, 10.0f, -20.0f, 10.0f, 10.0f);
        Entity[] entities = {
               // new WallEntity(10.0f, 0.0f, 20.0f, 10.0f),
                //new WallEntity(15.0f, -10.0f, 10.0f, 10.0f),
                new WallEntity(0.0f, -0.0f, 3.0f, 200.0f),
                new WallEntity(0.0f, -0.0f, 200.0f, 3.0f),
                new WallEntity(200.0f, -0.0f, 3.0f, 200.0f),
                new WallEntity(0.0f, -200.0f, 200.0f, 3.0f),

                new WallEntity(100.0f, -50.0f, 100.0f, 3.0f),
                new WallEntity(0.0f, -100.0f, 100.0f, 3.0f),


                //new WallEntity(10.0f, 0.0f, 20.0f, 40.0f), //apparing

                //new WallEntity(15.0f, -10.0f, 10.0f, 30.0f),
                new StdEntity(100.0f, Color.GREEN, new Rectangle(5, 50, 2, 2), 5.0f, -50.0f, 2.0f, 2.0f, 0.0f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f) // TODO: Rectangle is probably broken here
        };
        float[] goal = { 100.0f, -100.0f };
        Level level = new Level(mainEntity, entities, goal, new Vec2(0.0f, -30.0f));

        if (controller.loadLevel(level))
        {
            controller.setState(GameState.PLAY);
            Game.getInstance(controller).play();
        }
    }
}