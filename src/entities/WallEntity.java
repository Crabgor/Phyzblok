package entities;

import enums.Gravity;

import java.awt.*;

public class WallEntity extends Entity
{
    public WallEntity(float xModel, float yModel, float width, float height)
    {
        this.mass = 1.0f;
        this.colour = Color.GRAY;
        this.gravityType = Gravity.NONE;
        this.positionLocked = true;
        this.shape = new Rectangle((int) xModel,(int) yModel, (int) width, (int) height); // TODO : FIX SHAPE PARAMS
        this.xModel = xModel;
        this.yModel = yModel;
        this.width = width;
        this.height = height;
        this.rotation = 0.0f;

        // TODO : FIX COEFFICIENTS
        this.frictionCoeff = 0.0f;
        this.restitutionCoeff = 0.0f;
        this.gravityScale = 0.0f;
        this.linearDamping = 0.0f;
        this.angularDamping = 0.0f;
    }
}