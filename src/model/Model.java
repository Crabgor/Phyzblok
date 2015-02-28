package model;

import entities.*;
import levelGeneration.Level;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import java.util.ArrayList;
import java.util.List;

public class Model
{
	// region Constants
	private final float DEFAULT_DENSITY = 1.0f;
	// endregion

	// region Fields
	private World world;
	private Body mainBody;
	private MainEntity mainEntity;
	private List<Body> dynamicBodies;
	private List<Entity> dynamicEntities;
	// TODO: LIST OF DYNAMIC BODIES UNDER PLAYER GRAVITY
	private Vec2 currentGravity;
	// private float[] goalLocation;
	// endregion

	// region Getters
	public World getWorld()
	{
		return world;
	}


	public Body getMainBody()
	{
		return mainBody;
	}


	public List<Body> getDynamicBodies()
	{
		return dynamicBodies;
	}
	// endregion

	/**
	 *
	 */
	public Model()
	{
		world = null;
		mainBody = null;
		dynamicBodies = new ArrayList<Body>();
		currentGravity = new Vec2(0.0f, 0.0f);
	}


	/**
	 * 
	 * @param level
	 *
	 * @return	the success of level creation.
	 */
	public boolean buildLevel(Level level)
	{
		// TODO: GROUND BOX
		// Construct physics world + load physics bodies
		World w = new WorldBuilder().buildEnvironment(level);

		if (w == null || w.getBodyCount() <= 0) return false;

		// Get references to all dynamic bodies for visual updates
		Body b = w.getBodyList();

		mainBody = b;
		if (b.getNext() != null) b = b.getNext();
		while (b.getNext() != null)
		{
			if (b.getType() == BodyType.DYNAMIC)
				dynamicBodies.add(b);
			b = b.getNext();
		}

		world = w;
		return true;
	}


	/**
	 *
	 * @param x
	 * @param y
	 * @param mag
	 */
	public void applyPlayerGravity(int x, int y, int mag)
	{
		Vec2 gravity = new Vec2(x * mag, y * mag);
		if (currentGravity == gravity) return;
		currentGravity = gravity;
	}


	/**
	 *
	 */
	public void stepWorld(float timeStep, int velocityIt, int positionIt)
	{
		mainBody.applyForceToCenter(currentGravity);
		world.step(timeStep, velocityIt, positionIt);
		// TODO: OTHER STUFF
	}


	/**
	 *
	 */
	private class WorldBuilder
	{
		/**
		 *
		 * @param level
		 *
		 * Post-condition: 	The main entity is the first body in the World instance.
		 * 					All other entities added to the World instance.
		 */
		public World buildEnvironment(Level level)
		{
			World world = new World(level.getGravityVector());

			Entity e = level.getMainEntity();
			BodyDef bodyDef = constructBodyDef(e);
			Body body = world.createBody(bodyDef);
			FixtureDef fixtureDef = constructFixtureDef(e);
			body.createFixture(fixtureDef);
			body.m_mass = e.getMass();
			body.m_invMass = 1 / e.getMass();

			for (int i = 0; i < level.getEntities().length; i++)
			{
				e = level.getEntities()[i];
				bodyDef = constructBodyDef(e);
				body = world.createBody(bodyDef);
				fixtureDef = constructFixtureDef(e);
				body.createFixture(fixtureDef);
				body.m_mass = e.getMass();
				body.m_invMass = 1 / e.getMass();
			}

			return world;
		}


		/**
		 *
		 * @param entity
		 */
		private BodyDef constructBodyDef(Entity entity)
		{
			assert entity != null;
			BodyDef bodyDef = new BodyDef();
			bodyDef.position.set(entity.getxModel(), entity.getyModel());
			bodyDef.type = entity.isPositionLocked() ? BodyType.STATIC : BodyType.DYNAMIC;
			bodyDef.angle = entity.getRotation();
			bodyDef.gravityScale = entity.getGravityScale();
			bodyDef.angularDamping = entity.getAngularDamping();
			bodyDef.linearDamping = entity.getLinearDamping();
			// TODO: OTHERS?

			return bodyDef;
		}


		private FixtureDef constructFixtureDef(Entity entity)
		{
			assert entity != null;
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.friction = entity.getFrictionCoeff();
			fixtureDef.restitution = entity.getRestitutionCoeff();
			fixtureDef.density = DEFAULT_DENSITY;
			// TODO: OTHERS?

			return fixtureDef;
		}
	}
}