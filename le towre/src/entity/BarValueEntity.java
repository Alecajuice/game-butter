package entity;

import kinematics.Position;

import org.lwjgl.util.Dimension;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.*;
import world.World;

public class BarValueEntity extends ValueEntity
{
	int maxValue;
	Color backColor;
	
	public BarValueEntity(World world, Position pos, Dimension dim, int initialValue, int maxValue, Color frontColor, Color backColor) {
		super(world, pos, dim, initialValue, frontColor);
		this.backColor = backColor;
		this.maxValue = maxValue;
	}
	
	public void draw()
	{
		backColor.bind();
		glBegin(GL_QUADS);
		{
			glVertex2i(getX(), getY());
			glVertex2i(getX() + getWidth(), getY());
			glVertex2i(getX() + getWidth(), getY() + getHeight());
			glVertex2i(getX(), getY() + getHeight());
		}
		glEnd();
		
		frontColor.bind();
		glBegin(GL_QUADS);
		{
			glVertex2i(getX(), getY());
			glVertex2i(getX() + (int)(getWidth() * ((float)value/maxValue)), getY());
			glVertex2i(getX() + (int)(getWidth() * ((float)value/maxValue)), getY() + getHeight());
			glVertex2i(getX(), getY() + getHeight());
		}
		glEnd();
	}
}
