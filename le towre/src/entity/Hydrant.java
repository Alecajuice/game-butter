package entity;

import graphics.StaticSprite;

import org.lwjgl.util.Dimension;

import kinematics.Position;
import kinematics.VelocityVector;
import world.World;

public class Hydrant extends MobileEntity
{
	
	public Hydrant(World world, Position pos, Dimension dim)
	{
		super(world, pos, dim, new StaticSprite(StaticSprite.hydrant), new VelocityVector(0,0));
	}

	public boolean isSolid()
	{
		return false;
	}
	public void update() {
	}
}
