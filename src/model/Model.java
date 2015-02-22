package model;

import entities.Entity;
import levelGeneration.Level;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Model
{
	private World world;
	private float gravityMag;
	private Vec2 gravityVect;
	private Body mainBody;
	private Body[] playerGravityBodies;
	private Vec2[] goalLocation;

	public World getWorld() { return world; }
	public Body getMainBody() { return mainBody; }
	public Body[] getPlayerGravityBodies() { return playerGravityBodies; }


	/**
	 *
	 */
	public Model() {}


	/**
	 * 
	 * @param bodies
	 */
	private boolean buildWorld(BodyDef[] bodies)
	{
		// TODO - implement Model.buildWorld
		throw new UnsupportedOperationException();
	}


	/**
	 * 
	 * @param level
	 */
	public boolean buildLevel(Level level)
	{
		// TODO - implement Model.buildLevel
		throw new UnsupportedOperationException();
	}


	/**
	 *
	 */
	public void stepWorld()
	{

	}


	/**
	 *
	 */
	private static class WorldBuilder
	{
		/**
		 *
		 * @param entities
		 */
		public static World buildEnvironment(Entity[] entities)
		{
			// TODO - implement WorldBuilder.buildEnvironment
			throw new UnsupportedOperationException();
		}


		/**
		 *
		 * @param entity
		 */
		private static BodyDef constructBody(Entity entity)
		{
			BodyDef bodyDef = new BodyDef();

			// TODO - implement WorldBuilder.constructBody
			throw new UnsupportedOperationException();
		}
	}
}