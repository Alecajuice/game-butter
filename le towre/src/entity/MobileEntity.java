package entity;

import org.lwjgl.util.Dimension;

import graphics.Sprite;
import world.World;
import kinematics.*;

public abstract class MobileEntity extends Entity
{
	public static final int TERMINAL_VELOCITY = 20;
	
	private boolean markedForDestruction = false;
		protected void markForDestruction() {markedForDestruction = true;}
	
	// If you want this class to shut up, change this to false
	public boolean collisionVerbose = false;
	
	// this one keeps track if the mech has already used its jump
	public boolean jumped = false;
	public boolean isOnPlatform;
	
	public boolean isOnPlatform()
	{
		return isOnPlatform;
	}

	public void setOnPlatform(boolean isOnPlatform)
	{
		this.isOnPlatform = isOnPlatform;
	}
	
	protected VelocityVector vel;
		public double getXSpeed() {return vel.getX();}
		public void setXSpeed(double to) {vel.setX(to);}
		public double getYSpeed() {return vel.getY();}
		public void setYSpeed(double to) {vel.setY(to);}
		
		public double getSpeed() {return vel.getMag();}
		public void setSpeed(double newSpeed) {vel = new VelocityVector(vel.setMag(newSpeed));}
		
	protected Position previous = new Position(pos);
		public Position getPrevious() {return previous;}
		public int getPreviousX() {return previous.getX();}
		public int getPreviousY() {return previous.getY();}

	public MobileEntity(World world, Position pos)
	{
		super(world, pos);
		this.vel = new VelocityVector();
	}

	public MobileEntity(World world, Position pos, Dimension dim, Sprite spr, VelocityVector vel)
	{
		super(world, pos, dim, spr);
		this.vel = vel;
	}
	
	public boolean isMobile() {return true;}
	
	/**
	 * Updates the position of the mech
	 */
	public void update()
	{		
		if (getYSpeed() < TERMINAL_VELOCITY) setYSpeed(getYSpeed() + world.GRAVITY);
		Position futurePosition = determineFuturePosition();
		move(futurePosition);
		if(markedForDestruction) destroy();
	}

	//ALL OF THE CODE HERE IS OLD AND KEPT AROUND FOR JOHNNY'S SAKE (THOUGH I BORROWED FROM IT HEAVILY
	/**
	 * With a given VelocityVector, this will determine the future corners
	 * of the ActiveEntity
	 * @param checkVel the velocity
	 * @return the corners
	 */
	public Position[] getFutureCorners(VelocityVector checkVel) {
		Position[] Corners = new Position[4];
		Corners[0] = new Position((int) (getX() + checkVel.getX()),(int) (getY() + checkVel.getY()));
		Corners[1] = new Position((int) (getX() + getWidth() + checkVel.getX()),(int) (getY() + checkVel.getY()));
		Corners[2] = new Position((int) (getX() + getWidth() + checkVel.getX()),(int) (getY() + getHeight() + checkVel.getY()));
		Corners[3] = new Position((int) (getX() + checkVel.getX()),(int) (getY() + getHeight() + checkVel.getY()));
		return Corners;
	}
	
	/**
	 * With a given VelocityVector, this will determine if the ActiveEntity
	 * will collide with a SOLID object
	 * @param checkVel the velocity
	 * @return will it collide?
	 */
	public boolean isFutureCollisions(VelocityVector checkVel) {
		for(Entity entity : world.getEntities()) {
			if(rightColideType(entity) && !equals(entity) && willColide(entity, checkVel)) {
				interact(entity);
				return true;
			}
		}
		return false;
	}
	
	public void interact(Entity entity) {}

	protected boolean rightColideType(Entity entity)
	{
		return entity instanceof Platform;
	}
	
	/**
	 * This lengthy method will determine the future position for the ActiveEntity
	 * It will also set the jumped value
	 * jumped determines sif the ActiveEntity still has its jump left
	 * @return the future position (after update)
	 */
	private Position determineFuturePosition() {
		if(vel.getX() == 0 && vel.getY() == 0) {
			return pos;
		}
		VelocityVector velVec = new VelocityVector(vel);
		boolean firstCheck = true;
		for(int i = 0; i <= Math.abs(vel.getX()); i++) {
			velVec.setY(vel.getY());
			for(int j = 0; j <= Math.abs(vel.getY()); j++) {
				if(collisionVerbose) {
					System.out.println("Actual: " + vel.getX() + " " + vel.getY() + " Tested: " + velVec.getX() + " " + velVec.getY());
				}
				if(!isFutureCollisions(velVec)) {
					if(collisionVerbose) {
						System.out.println("No collision ");
					}
					if(!firstCheck) { // this will only happen if the object collides
						if(getYSpeed() > 0)jumped = false;
						//vel.setY(0); 
					}
					return new Position((int) (getX() + velVec.getX()),(int) (getY() + velVec.getY()));
				}
				else {
					firstCheck = false;
					if(velVec.getY() > 0) {
						velVec.setY(velVec.getY() - 1);
					}
					if(velVec.getY() < 0) {
						velVec.setY(velVec.getY() + 1);
					}
					if(collisionVerbose) {
						System.out.println("Collision");
					}
				}
			}
			if(velVec.getX() > 0) {
				velVec.setX(velVec.getX() - 1);
			}
			if(velVec.getX() < 0) {
				velVec.setX(velVec.getX() + 1);
			}
		}
		if(collisionVerbose) {
			System.err.println("Collision Error 1 " + velVec.getX() + " " + velVec.getY());
		}
		return new Position((int) (pos.getX() + Math.random()*32 - 16), (int) (pos.getY() - Math.random()*32));

	}
	
	/**
	 * This determines if the given ActiveEntity will collide with a given Entity
	 * if the ActiveEntity is moving at a certain given VelocityVector
	 * @param entity the entity that the ActiveEntity may be colliding with
	 * @param checkVel the velocity of the active entity
	 * @return will they collide?
	 */
	protected boolean willColide(Entity entity, VelocityVector checkVel) {
		Position[] corners = entity.getCorners();
		Position[] fcorners = getFutureCorners(checkVel);
		for(int i = 0; i <= 3; i++) {
			if(fcorners[0].getX() <= corners[i].getX() && corners[i].getX() <= fcorners[2].getX()) {
				if(fcorners[0].getY() <= corners[i].getY() && corners[i].getY() <= fcorners[2].getY()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Moves the ActiveEntity to the future Position and sets the current Position
	 * to the previous Position
	 * @param future the future position
	 */
	public final void move(Position future)
	{
		previous.setX(getX());
		previous.setY(getY());
		setX((int) (future.getX()));
		setY((int) (future.getY()));
	}
}
