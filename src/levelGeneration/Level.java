package levelGeneration;

import entities.*;
import org.jbox2d.common.Vec2;

public class Level
{
	// region Fields
	private MainEntity mainEntity;
	private Entity[] entities;
	private float[] goalLocation;
	private Vec2 gravityVector;
	// endregion

	// region Getters
	public MainEntity getMainEntity()
	{
		return mainEntity;
	}


	public Entity[] getEntities()
	{
		return entities;
	}


	public float[] getGoalLocation()
	{
		return goalLocation;
	}


	public Vec2 getGravityVector()
	{
		return gravityVector;
	}
	// endregion

	/**
	 *
	 * @param mainEntity
	 * @param entities
	 * @param goalLocation
	 */
	public Level(MainEntity mainEntity, Entity[] entities, float[] goalLocation, Vec2 gravityVector)
	{
		this.mainEntity = mainEntity;
		this.entities = entities;
		this.goalLocation = goalLocation;
		this.gravityVector = gravityVector;
	}
}