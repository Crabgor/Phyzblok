package entities;

import java.awt.*;
import enums.Gravity;

public class Entity
{
	private float mass;
	private Color colour;
	private Gravity gravityType;
	private boolean positionLocked;
	private Shape shape;
	//private Coordinates position;
	private float xModel, yModel;
	private float width, height;
	private float rotation;
	private float frictionCoeff;
	private float restitutionCoeff;
	private float gravityScale;
	private float linearDamping;
	private float angularDamping;


	/**
	 *
	 */
	public Entity() {}


	/**
	 *
	 * @return
	 */
	public int[] getViewPosition()
	{
		int[] viewPosition = { (int) (xModel - width / 2), (int) (yModel - height / 2) };
		return viewPosition;
	}
}