package view;

import entities.Entity;
import entities.ViewEntity;

import java.awt.*;

/**
 * Created by Cregnacht on 2015-03-02.
 */
public class PhyzRectangle extends Rectangle
{
    private ViewEntity entity;

    public ViewEntity getEntity()
    {
        return entity;
    }

    public void setEntity(ViewEntity e)
    {
        entity = e;
    }


    /**
     *
     * @param entity
     *
     * Pre-condition: The shape of the given entity is a Rectangle.
     */
    public PhyzRectangle(ViewEntity entity)
    {
        super((Rectangle) entity.getShape());
        this.entity = entity;
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
