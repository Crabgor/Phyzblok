package entities;

import org.jbox2d.dynamics.Body;
import org.w3c.dom.css.Rect;

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
     * @return  The X position of the entity, converted to the View coordinate system.
     *
     *          Note: The model shapes use half-width and half-height as the dimensions,
     *          taking them to be a distance from the centre point of a body.
     *          View elements use the top left corner, so the conversion uses half
     *          the dimensions and adds/subtracts these values as necessary.
     *
     * Post-condition: The position is converted to the View coordinate system.
     *                 The value may need to be rounded appropriately to clean up
     *                 floating point errors.
     */
    public float getX()	// Only view/controller need this
    {
        return physicsBody.getPosition().x - width/2;
    }


    /**
     *
     * @return  The Y position of the entity, converted to the View coordinate system.
     *
     *          Note: The model shapes use half-width and half-height as the dimensions,
     *          taking them to be a "radius" from the centre point of a body.
     *          View elements use the top left corner, so the conversion uses half
     *          the dimensions and adds/subtracts these values as necessary.
     *
     * Post-condition: The position is converted to the View coordinate system.
     *                 The value may need to be rounded appropriately to clean up
     *                 floating point errors.
     */
    public float getY() // Only view/controller need this
    {
        return (physicsBody.getPosition().y + height/2) * -1;
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


    /**
     *
     * @param shape
     * @param colour
     * @param physicsBody
     * @param width
     * @param height
     */
    public ViewEntity(Shape shape, Color colour, Body physicsBody, int width, int height)
    {
        this.shape = shape;
        this.colour = colour;
        this.physicsBody = physicsBody;
        this.width = width;
        this.height = height;
        shapeConstructor();
    }


    /**
     *
     * @param entity
     */
    public ViewEntity(Entity entity)
    {
        this.shape = entity.getShape();
        this.colour = entity.getColour();
        this.physicsBody = entity.getPhysicsBody();
        this.width = (int) entity.getWidth();
        this.height = (int) entity.getHeight();
        shapeConstructor();
    }


    /**
     *
     */
    private void shapeConstructor()
    {
        String s = this.shape.toString();
        if (s == new Rectangle().toString())
        {
            Rectangle tempShape = (Rectangle) this.shape;
            tempShape.setLocation((int) getX(), (int) getY());
            tempShape.setSize(getWidth(), getHeight());
            this.shape = tempShape;
        }
    }
}
