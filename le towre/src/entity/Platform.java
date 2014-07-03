package entity;

import graphics.Sprite;
import graphics.StaticSprite;

import org.lwjgl.util.Dimension;

import kinematics.Position;
import world.World;

public class Platform extends InactiveEntity
{	
	public Platform(World world, Position pos, Dimension dim)
	{
		super(world, pos, dim, new StaticSprite(StaticSprite.grass));
	}

	public boolean isSolid()
	{
		return true;
	}
}
