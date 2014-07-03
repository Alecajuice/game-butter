package entity;

import graphics.GameFont;
import graphics.Sprite;
import kinematics.Position;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Dimension;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.*;
import world.World;

public class ValueEntity extends InactiveEntity {

	private GameFont font = GameFont.defaultFont20pt;
	protected int value = 0;
	Color frontColor;

	public ValueEntity(World world, Position pos, Dimension dim, int initialValue, Color frontColor)
	{
		super(world, pos, dim, null);
		value = initialValue;
		this.frontColor = frontColor;
	}

	public boolean isSolid() {return false;}
	
	public void draw()
	{
		font.draw(getX(), getY(), 20, value + "", frontColor);
		glDisable(GL11.GL_TEXTURE_2D);
	}
	
	public void updateValue(int newValue)
	{
		value = newValue;
	}
}
