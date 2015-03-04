package entities;

import enums.Gravity;

import java.awt.*;

public class StdEntity extends Entity
{
    public StdEntity(float mass, Color colour, Shape shape, float xModel, float yModel,
                     float width, float height, float rotation,
                     float frictionCoeff, float restitutionCoeff, float gravityScale,
                     float linearDamping, float angularDamping)
    {
        this.mass = mass;
        this.colour = colour;
        this.gravityType = Gravity.STANDARD;
        this.positionLocked = false;
        this.shape = shape;
        this.xModel = xModel;
        this.yModel = yModel;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.frictionCoeff = frictionCoeff;
        this.restitutionCoeff = restitutionCoeff;
        this.gravityScale = gravityScale;
        this.linearDamping = linearDamping;
        this.angularDamping = angularDamping;
    }
}