package entities;

import org.jbox2d.dynamics.Body;

import java.awt.*;

/**
 * Created by Cregnacht on 2015-03-02.
 */
public class ViewEntity
{
    // region Fields
    private Shape shape;
    private Color colour;
    private Body  physicsBody;
    private int   width;
    private int   height;
    // endregion

    // region Getters
    public Shape getShape()
    {
        return shape;
    }


    public Color getColour()
    {
        return colour;
    }


    /**
     *
     * @return The X position of the entity, converted to the View coordinate system.
     *
     * Post-condition: The position is converted to the View coordinate system.
     */
    public float getX()	// Only view/controller need this
    {
        return physicsBody.getPosition().x;
        // TODO: Change to View coordinate system
    }


    /**
     *
     * @return The Y position of the entity, converted to the View coordinate system.
     *
     * Post-condition: The position is converted to the View coordinate system.
     */
    public float getY() // Only view/controller need this
    {
        return physicsBody.getPosition().y;
        // TODO: Change to View coordinate system
    }


    /**
     *
     * @return
     */
    public float getAngle() // Only view/controller need this
    {
        return physicsBody.getAngle();
    }


    /**
     *
     * @return
     */
    public int getWidth()
    {
        return width;
    }


    /**
     *
     * @return
     */
    public int getHeight()
    {
        return height;
    }
    // endregion

    // region Setters

    // endregion


    public ViewEntity(Shape shape, Color colour, Body physicsBody, int width, int height)
    {
        this.shape = shape;
        this.colour = colour;
        this.physicsBody = physicsBody;
        this.width = width;
        this.height = height;
    }


    public ViewEntity(Entity entity)
    {
        this.shape = entity.getShape();
        this.colour = entity.getColour();
        this.physicsBody = entity.getPhysicsBody();
        this.width = (int) entity.getWidth();
        this.height = (int) entity.getHeight();
    }


}
