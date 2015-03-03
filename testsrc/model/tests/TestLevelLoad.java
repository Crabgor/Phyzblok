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
public class TestLevelLoad
{
    @Test
    public void main()
    {
        System.out.println("Testing level loading!");

        MainEntity mainEntity = new MainEntity(10.0f, 30.0f, -10.0f, 2.0f, 2.0f);
        Entity[] entities = {
                new WallEntity(10.0f, 0.0f, 20.0f, 40.0f),
                new WallEntity(40.0f, 0.0f, 20.0f, 40.0f),
                new StdEntity(10.0f, Color.black, 5.0f, 10.0f, 2.0f, 2.0f, 0.0f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f)
        };
        float[] goal = { 100.0f, -100.0f };
        Level level = new Level(mainEntity, entities, goal, new Vec2(0.0f, -30.0f));

        System.out.println("Level created!");

        Model model = new Model();
        try { model.buildLevel(level); }
        catch (Exception e) { System.out.println(e.getMessage() + " " + e.toString()); }

        System.out.println("Level generation successful. The following entities exist:\n");

        System.out.println("Main Entity: X = " + model.getMainEntity().getX() + " ; Y = " + model.getMainEntity().getY() + "; Width = " + model.getMainEntity().getWidth() + "; Height = " + model.getMainEntity().getHeight());
        for (ViewEntity v : model.getDynamicEntities())
        {
            System.out.println("Dynamic Entity: X = " + v.getX() + " ; Y = " + v.getY() + "; Width = " + v.getWidth() + "; Height = " + v.getHeight());
        }
        for (ViewEntity v : model.getStaticEntities())
        {
            System.out.println("Static Entity: X = " + v.getX() + " ; Y = " + v.getY() + "; Width = " + v.getWidth() + "; Height = " + v.getHeight());
        }
    }
}
