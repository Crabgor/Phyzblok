package model;

import controller.Controller;
import entities.*;
import levelGeneration.Level;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import java.util.ArrayList;
import java.util.List;

public class Model
{
	// region Constants
	private final float PLAYER_GRAVITY_ACCELERATION_SCALE = 5.0f;
	private final float DEFAULT_DENSITY = 1.0f;

	private final float WALL_FRICTION = 0.5f;
	private final float WALL_RESTITUTION = 0.25f;
	// endregion

	// region Fields
	private Controller controller;

	private World world;
	private Body mainBody;
	private ViewEntity mainEntity;
	private List<ViewEntity> dynamicEntities;
	private List<ViewEntity> staticEntities;
	// TODO: LIST OF DYNAMIC BODIES UNDER PLAYER GRAVITY
	private Vec2 currentGravity;
	// endregion

	// region Getters
	public Controller getController()
	{
		return controller;
	}

	public World getWorld()
	{
		return world;
	}


	public ViewEntity getMainEntity()
	{
		return mainEntity;
	}


	public List<ViewEntity> getDynamicEntities()
	{
		return dynamicEntities;
	}


	public List<ViewEntity> getStaticEntities()
	{
		return staticEntities;
	}
	// endregion

	// region Setters
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	// endregion


	/**
	 *
	 */
	public Model()
	{
		world = null;
		mainBody = null;
		mainEntity = null;
		dynamicEntities = new ArrayList<ViewEntity>();
		staticEntities = new ArrayList<ViewEntity>();
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
		// Construct physics world + load physics bodies
		World w = new WorldBuilder().buildEnvironment(level);

		if (w == null || w.getBodyCount() <= 0) return false;

		// Get references to first body in list, which must be the Main Body
		mainBody = w.getBodyList();
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
		mag *= PLAYER_GRAVITY_ACCELERATION_SCALE;
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
		// TODO: Apply forces to other player-gravity dynamic bodies if/when they exist
		world.step(timeStep, velocityIt, positionIt);
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
			body.m_mass = e.getMass();
			body.m_invMass = 1 / e.getMass();
			body.createFixture(fixtureDef);
			mainBody = body;
			e.setPhysicsBody(mainBody);
			mainEntity = new ViewEntity(e);

			for (int i = 0; i < level.getEntities().length; i++)
			{
				e = level.getEntities()[i];
				bodyDef = constructBodyDef(e);
				body = world.createBody(bodyDef);
				fixtureDef = constructFixtureDef(e);
				body.createFixture(fixtureDef);
				body.m_mass = e.getMass();
				body.m_invMass = 1 / e.getMass();
				e.setPhysicsBody(body);
				ViewEntity v = new ViewEntity(e);

				if (body.getType() == BodyType.DYNAMIC)
					dynamicEntities.add(v);
				else if (body.getType() == BodyType.STATIC)
					staticEntities.add(v);
			}
			
			world = addStandardWalls(world);

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

			return bodyDef;
		}


		/**
		 *
		 * @param entity
		 * @return
		 */
		private FixtureDef constructFixtureDef(Entity entity)
		{
			assert entity != null;
			FixtureDef fixtureDef = new FixtureDef();

			// TODO: HANDLE CIRCLES + OTHER SHAPES
			PolygonShape polygonShape = new PolygonShape();
			polygonShape.setAsBox(entity.getWidth(), entity.getHeight());

			fixtureDef.shape = polygonShape;
			fixtureDef.friction = entity.getFrictionCoeff();
			fixtureDef.restitution = entity.getRestitutionCoeff();
			fixtureDef.density = DEFAULT_DENSITY;

			return fixtureDef;
		}


		/**
		 *
		 * @param world
		 * @return
		 */
		private World addStandardWalls(World world)
		{
			BodyDef bd = new BodyDef();
			Body 	ground 	  = getWorld().createBody(bd),
					leftWall  = getWorld().createBody(bd),
					rightWall = getWorld().createBody(bd),
					ceiling   = getWorld().createBody(bd);

			// Dimensions for the bounding box
			// Currently a 200x100 area
			float 	topHeight = 100.0f,
					bottomHeight = 0.0f,
					leftWidth = -100.0f,
					rightWidth = 100.0f;

			// Create bounding box dimensioned according to above numbers
			Vec2	groundLeft = new Vec2(leftWidth, bottomHeight), groundRight = new Vec2(rightWidth, bottomHeight),
					leftWallTop = new Vec2(leftWidth, topHeight), leftWallBottom = new Vec2(leftWidth, bottomHeight),
					rightWallTop = new Vec2(rightWidth, topHeight), rightWallBottom = new Vec2(rightWidth, bottomHeight),
					ceilingLeft = new Vec2(leftWidth, topHeight), ceilingRight = new Vec2(rightWidth, topHeight);

			EdgeShape shape = new EdgeShape();
			FixtureDef fd = new FixtureDef();
			fd.density = 0.0f;
			fd.friction = WALL_FRICTION;
			fd.restitution = WALL_RESTITUTION;

			// Ground
			shape.set(groundLeft, groundRight);
			fd.shape = shape;
			ground.createFixture(fd);

			// Left Wall
			shape.set(leftWallTop, leftWallBottom);
			fd.shape = shape;
			leftWall.createFixture(fd);

			// Right Wall
			shape.set(rightWallTop, rightWallBottom);
			fd.shape = shape;
			rightWall.createFixture(fd);

			// Ceiling
			shape.set(ceilingLeft, ceilingRight);
			fd.shape = shape;
			ceiling.createFixture(fd);

			return world;
		}
	}
}
