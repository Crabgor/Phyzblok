package entities;

import enums.Gravity;

import java.awt.*;

public class MainEntity extends Entity
{
	public MainEntity(float mass, float xModel, float yModel, float width, float height)
	{
		this.mass = mass;
		this.colour = Color.RED;
		this.gravityType = Gravity.PLAYER;
		this.positionLocked = false;
		this.shape = new Rectangle(); // TODO : FIX SHAPE PARAMS
		this.xModel = xModel;
		this.yModel = yModel;
		this.width = width;
		this.height = height;

		// TODO : FIX COEFFICIENTS. ALSO, ROTATION????? WAT.
		this.rotation = 0.0f;
		this.frictionCoeff = 0.0f;
		this.restitutionCoeff = 0.0f;
		this.gravityScale = 0.0f;
		this.linearDamping = 0.0f;
		this.angularDamping = 0.0f;
	}
}