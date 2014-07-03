package entity;

import org.lwjgl.util.Dimension;

import graphics.Sprite;
import kinematics.Position;
import world.World;

public abstract class InactiveEntity extends Entity
{
	public InactiveEntity(World world, Position pos, Dimension dim, Sprite spr)
	{
		super(world, pos, dim, spr);
	}
	
	public boolean isMobile() {return false;}
}
