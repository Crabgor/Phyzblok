package view;

import entities.Entity;
import entities.ViewEntity;

import java.awt.*;

/**
 * Created by Cregnacht on 2015-03-02.
 */
public class PhyzRectangle extends Rectangle
{
    private ViewEntity entity;  // TODO: Change to ViewEntity if that works better

    public ViewEntity getEntity()
    {
        return entity;
    }

    public void setEntity(ViewEntity e)
    {
        entity = e;
    }


    /**
     * Updates the position of the shape using the position of the corresponding physics body.
     *
     * Post-condition: The location of the shape is modified.
     */
    public void updatePosition()
    {
        this.setLocation((int) entity.getX(), (int) entity.getY());
        // TODO: ROTATION
    }
}
