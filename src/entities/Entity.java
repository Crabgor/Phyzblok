package entities;

import java.awt.*;
import enums.Gravity;

/**
 * An abstract representation of a physical entity used for generating
 * physical bodies in the physics engine (held within the model), and
 * their appropriate graphical representation (within the view).
 */
public abstract class Entity
{
	// region Fields
	protected float 	mass;
	protected Color 	colour;
	protected Gravity 	gravityType;
	protected boolean 	positionLocked;
	protected Shape 	shape;
	protected float 	xModel, yModel;
	protected float 	width;
	protected float 	height;
	protected float 	rotation;
	protected float 	frictionCoeff;
	protected float 	restitutionCoeff;
	protected float 	gravityScale;
	protected float 	angularDamping;
	protected float 	linearDamping;
	// endregion

	// region Getters
	public float getMass()
	{
		return mass;
	}


	public Color getColour()
	{
		return colour;
	}


	public Gravity getGravityType()
	{
		return gravityType;
	}


	public boolean isPositionLocked()
	{
		return positionLocked;
	}


	public Shape getShape()
	{
		return shape;
	}


	public float getxModel()
	{
		return xModel;
	}


	public float getyModel()
	{
		return yModel;
	}


	public float getxView()
	{
		return xModel - width / 2;
	}


	public float getyView()
	{
		return yModel - height / 2;
	}


	public float getWidth()
	{
		return width;
	}


	public float getHeight()
	{
		return height;
	}


	public float getRotation()
	{
		return rotation;
	}


	public float getFrictionCoeff()
	{
		return frictionCoeff;
	}


	public float getRestitutionCoeff()
	{
		return restitutionCoeff;
	}


	public float getGravityScale()
	{
		return gravityScale;
	}


	public float getLinearDamping()
	{
		return linearDamping;
	}


	public float getAngularDamping()
	{
		return angularDamping;
	}
	// endregion

	public Entity()
	{
		this.frictionCoeff = 0.0f;
		this.restitutionCoeff = 0.0f;
		this.gravityScale = 0.0f;
		this.linearDamping = 0.0f;
		this.angularDamping = 0.0f;
	}
}