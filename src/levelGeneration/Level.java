package levelGeneration;

import entities.Entity;

public class Level
{
	private Entity mainEntity;
	private Entity[] entities;
	private float[] goalLocation;

	public Entity getMainEntity() { return mainEntity; }
	public Entity[] getEntities() { return entities; }
	public float[] getGoalLocation() { return goalLocation; }


	/**
	 *
	 * @param mainEntity
	 * @param entities
	 * @param goalLocation
	 */
	public Level(Entity mainEntity, Entity[] entities, float[] goalLocation)
	{
		this.mainEntity = mainEntity;
		this.entities = entities;
		this.goalLocation = goalLocation;
	}
}