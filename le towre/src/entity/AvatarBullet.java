package entity;

import graphics.StaticSprite;
import kinematics.Position;
import kinematics.Vector;
import kinematics.VelocityVector;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Dimension;

import world.World;

public class AvatarBullet extends Projectile
{
	private final static int SPEED = 70;

	public AvatarBullet(World world, Position pos)
	{
		super(world, pos);
	}
	
	public AvatarBullet(World world, Position pos, VelocityVector vel)
	{
		super(world, pos, new Dimension(4,4), new StaticSprite(StaticSprite.bullet), vel);
	}
	
	public AvatarBullet(World world, Position pos, Position target)
	{
		this(world, pos, new VelocityVector(new Vector(pos, target).setMag(SPEED)));
	}
}
