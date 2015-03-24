package model.tests;

import entities.*;
import levelGeneration.Level;
import model.Model;
import org.jbox2d.common.Vec2;
import org.junit.Test;

import java.awt.*;

/**
 * Created by Cregnacht on 2015-03-02.
 */
public class TestWorldStep
{
    @Test
    public void main()
    {
        Model model = new Model(null);
        int iterationCount = 10;

        System.out.println("Testing level loading!");

        //MainEntity mainEntity = new MainEntity(10.0f, 30.0f, -10.0f, 2.0f, 2.0f);
        //Entity[] entities = {
        //        new WallEntity(10.0f, 0.0f, 20.0f, 40.0f),
        //        new WallEntity(40.0f, 0.0f, 20.0f, 40.0f),
        //        new StdEntity(100.0f, Color.black, new Rectangle(5, 10, 2, 2), -99.0f, 97.0f, 2.0f, 2.0f, 0.0f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f)
        //};

        MainEntity mainEntity = new MainEntity(5.0f, 5.0f, -20.0f, 8.0f, 8.0f);
        Entity[] entities = {
                new WallEntity(15.0f, -75.0f, 30.0f, 70.0f),
                new WallEntity(95.0f, -55.0f, 30.0f, 30.0f),
                new WallEntity(95.0f, -95f, 30.0f, 30.0f),
                new WallEntity(100.0f, -40.0f, 2.0f, 10.0f),
                //new StdEntity(5f, new Color(200, 0, 255), new Rectangle(10, 5, 10, 3), 10.0f, -5.0f, 10.0f, 2.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f),
                new StdEntity(25.0f, new Color(200, 0, 255), new Rectangle(27, 40, 15, 10), 27.0f, -40.0f, 15.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.000f, 0.0f, 0.0f),
                new StdEntity(10.0f, new Color(200, 0, 255), new Rectangle(82, 25, 5, 5), 82.0f, -25.0f, 5.0f, 5.0f, 0.0f, 0.0f, 0.0f, 0.000f, 0.0f, 0.0f)
        };

        float[] goal = { 100.0f, -100.0f };
        Level level = new Level(mainEntity, entities, goal, new Vec2(0.0000f, -30.0000f), 10);

        System.out.println("Level created!");

        try { model.buildLevel(level); }
        catch (Exception e) { System.out.println(e.getMessage() + " " + e.toString()); }

        System.out.println("Level generation successful. Running " + iterationCount + " steps");

        for (int i = 0; i < iterationCount; i++)
        {
            model.stepWorld(1/30.0f, 1, 1);

            System.out.println("Main Entity: X = " + model.getMainEntity().getX() + " ; Y = " + model.getMainEntity()
                    .getY() + "; Width = " + model.getMainEntity().getWidth() + "; Height = " + model.getMainEntity()
                                       .getHeight());
            for (ViewEntity v : model.getDynamicEntities())
                System.out.println("Dynamic Entity: X = " + v.getX() + " ; Y = " + v.getY() + "; Width = " + v
                        .getWidth() + "; Height = " + v.getHeight());

            for (ViewEntity v : model.getStaticEntities())
                System.out.println("Static Entity: X = " + v.getX() + " ; Y = " + v.getY() + "; Width = " + v
                        .getWidth() + "; Height = " + v.getHeight());

        }

        System.out.println("\nNow testing 1000 iterations on a dynamic entity.");
        ViewEntity v = model.getDynamicEntities().get(0);

        for (int i = 0; i < 1000; i++)
        {
            model.stepWorld(1/30.0f, 1, 1);
            System.out.println(i + ":   " + "Dynamic Entity: X = " + v.getX() + " ; Y = " + v.getY() + "; Width = " + v
                    .getWidth() + "; Height = " + v.getHeight());
        }
    }
}
