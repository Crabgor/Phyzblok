package model;

import controller.Controller;
import entities.Entity;
import entities.ViewEntity;
import enums.GameState;
import interfaces.IModelView;
import levelGeneration.Level;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.ArrayList;
import java.util.List;

public class Model implements IModelView
{
	// region Constants
	private final float PLAYER_GRAVITY_ACCELERATION_SCALE = 1.0f;
	private final float DEFAULT_DENSITY = 1.0f;

	private final float WALL_FRICTION = 0.5f;
	private final float WALL_RESTITUTION = 0.25f;

	// Dimensions for the bounding box
	// Currently a 200x100 area
	// Top-Left corner is 0,0
	public final float 	TOP_HEIGHT = 0.000f,
						BOTTOM_HEIGHT = -110.000f,
						LEFT_WIDTH = 0.000f,
						RIGHT_WIDTH = 110.000f;

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


	/**
	 *
	 */
	public Model(Controller c)
	{
		controller = c;
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
		// mainBody = w.getBodyList();
		// THE ABOVE BREAKS THINGS. IS IT EVEN NECESSARY?
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
		mag *= PLAYER_GRAVITY_ACCELERATION_SCALE * mainBody.m_mass;	// Create force from acceleration
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
	}


	@Override
	public void changeState(GameState state)
	{
		switch (state)
		{
			case EXITING:
				break;
			case LEVEL_SELECT:
				break;
			case LOADING:
				break;
			case MAIN_MENU:
				break;
			case PAUSE:
				break;
			case PLAY:
				break;
			default:
				break;
		}
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
			bodyDef.fixedRotation = true;

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
			polygonShape.setAsBox(entity.getWidth()/2, entity.getHeight()/2); // Params are half-width/half-height

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
			Body 	ground 	  = world.createBody(bd),
					leftWall  = world.createBody(bd),
					rightWall = world.createBody(bd),
					ceiling   = world.createBody(bd);

			// Create bounding box dimensioned according to above numbers
			Vec2	bottomLeft = new Vec2(LEFT_WIDTH, BOTTOM_HEIGHT),
					bottomRight = new Vec2(RIGHT_WIDTH, BOTTOM_HEIGHT),
					topLeft = new Vec2(LEFT_WIDTH, TOP_HEIGHT),
					topRight = new Vec2(RIGHT_WIDTH, TOP_HEIGHT);

			EdgeShape shape = new EdgeShape();
			FixtureDef fd = new FixtureDef();
			fd.density = 0.0f;
			fd.friction = WALL_FRICTION;
			fd.restitution = WALL_RESTITUTION;

			// Ground
			shape.set(bottomLeft, bottomRight);
			fd.shape = shape;
			ground.createFixture(fd);

			// Left Wall
			shape.set(topLeft, bottomLeft);
			fd.shape = shape;
			leftWall.createFixture(fd);

			// Right Wall
			shape.set(topRight, bottomRight);
			fd.shape = shape;
			rightWall.createFixture(fd);

			// Ceiling
			shape.set(topLeft, topRight);
			fd.shape = shape;
			ceiling.createFixture(fd);

			return world;
		}
	}
}
