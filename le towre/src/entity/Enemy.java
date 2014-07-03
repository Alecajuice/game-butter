package entity;

import org.lwjgl.util.Dimension;

import kinematics.Position;
import kinematics.VelocityVector;
import world.World;
import entity.Mob;
import graphics.Sprite;

public abstract class Enemy extends Mob {
	
	protected double speed;
	protected double jumpSpeed;
	private int damage = 0;
	
	public Enemy(World world, Position pos, Dimension dimension, Sprite sprite, VelocityVector vector, int maxHealth) {
		super(world, pos, dimension, sprite, vector, maxHealth);
	}
	protected abstract void ai();
	
	protected boolean rightColideType(Entity entity)
	{
		return entity instanceof Platform || entity instanceof Mob;
	}
	
	public void update()
	{
		damage(damage);
		damage = 0;
		super.update();
	}
	
	public void prepDamage(int i) {
		damage = i;
	}
}
