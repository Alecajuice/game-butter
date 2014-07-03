package entity;

import graphics.Sprite;
import kinematics.*;
import world.World;

import org.lwjgl.util.Dimension;

public abstract class Mob extends MobileEntity
{
	protected boolean isOnPlatform;
	protected final int MAX_HEALTH;
	
	private boolean markedForDeath = false;
	protected void markForDeath() {markedForDeath = true;}
	
	protected int health;
		public int getHealth() {return health;}
		private void setHealth(int newHealth) {health = newHealth;}

	public Mob(World world, Position pos, int maxHealth)
	{
		super(world, pos);
		MAX_HEALTH = maxHealth;
		setHealth(MAX_HEALTH);
	}

	public Mob(World world, Position pos, Dimension dim, Sprite spr, VelocityVector vel, int maxHealth)
	{
		super(world, pos, dim, spr, vel);
		MAX_HEALTH = maxHealth;
		setHealth(MAX_HEALTH);
	}
	
	public void damage(int amount)
	{
		health-=amount;
		if(health <= 0) markForDeath();
	}
	
	protected void die()
	{
		destroy();
	}
	public boolean isSolid()
	{
		return true;
	}
	public final void offScreenDeath() {
		if(getX() > world.getLevelWidth()+64) {
			die();
		}
		if(getX() + getWidth()< -64) {
			die();
		}
		if(getY() > world.getLevelHeight() + 64) {
			die();
		}
	}
	public void update() {
		super.update();
		offScreenDeath();
		if(markedForDeath) die();
	}
}
