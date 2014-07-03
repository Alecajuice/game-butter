package entity;

import java.awt.Rectangle;

import org.lwjgl.util.Dimension;

import graphics.Drawable;
import graphics.Sprite;
import graphics.StaticSprite;
import world.World;
import kinematics.Position;

public abstract class Entity implements Drawable {
	protected Position pos;
		public int getX() {return pos.getX();}
		public void setX(int x) {pos.setX(x);}
		public int getY() {return pos.getY();}
		public void setY(int y) {pos.setY(y);}

	protected Dimension dim;
		public int getWidth() {return dim.getWidth();}
		public void setWidth(int w) {dim.setWidth(w);}
		public int getHeight() {return dim.getHeight();}
		public void setHeight(int h) {dim.setHeight(h);}

	private boolean visible;
		public boolean isVisible() {return visible;}
		public void setVisible(boolean visible) {this.visible = visible;}

	public Sprite sprite;
	public Sprite getSprite()
	{
		/*if(world.avatar.sprite == sprite && getWidth() != 128)
		{
			try
			{
				throw(new Exception("ya"));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/
		return sprite;
	}
	public void setSprite(Sprite sprite) {this.sprite = sprite;}

	protected World world;
		public World getWorld(){return world;}

	
	public Entity(World world, Position pos)
	{
		this(world, pos, new Dimension(32, 32), new StaticSprite(StaticSprite.dirt));
	}

	public Entity(World world, Position pos, Dimension dim, Sprite spr)
	{
		this.pos = pos;
		this.dim = dim;
		this.sprite = spr;
		this.world = world;
		world.add(this);
	}

	public void destroy()
	{
		world.delete(this);
	}

	public abstract boolean isSolid();
	public abstract boolean isMobile();

	public Rectangle getCollisionBox()
	{
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}


	public void draw()
	{
		getSprite().draw(getX(), getY(), getWidth(), getHeight());
	}

	public boolean canStandOn()
	{
		return !isMobile();
	}
	
	public Position[] getCorners() {
		Position[] Corners = new Position[4];
		Corners[0] = new Position((int) (getX()),(int) (getY()));
		Corners[1] = new Position((int) (getX() + getWidth()),(int) (getY()));
		Corners[2] = new Position((int) (getX() + getWidth()),(int) (getY() + getHeight()));
		Corners[3] = new Position((int) (getX()),(int) (getY() + getHeight()));
		return(Corners);
	}
}
