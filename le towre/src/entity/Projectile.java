package entity;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.Line2D;

import graphics.Sprite;
import graphics.StaticSprite;
import graphics.AnimatedSprite;
import kinematics.Position;
import kinematics.VelocityVector;
import main.SuperMechz10000;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Dimension;

import audio.Sound;
import particles.Particle;
import world.World;

public abstract class Projectile extends MobileEntity
{	
	public Projectile(World world, Position pos)
	{
		super(world, pos);
	}

	public Projectile(World world, Position pos, VelocityVector vel)
	{
		super(world, pos, new Dimension(4,4), new StaticSprite(StaticSprite.bullet), vel);
	}

	public Projectile(World world, Position pos, Position target, int speed)
	{
		super(world, pos);
	}

	public Projectile(World world, Position pos, Position target, int speed, int spread)
	{
		super(world, pos);
	}

	public Projectile(World world, Position pos, Dimension dim, Sprite spr, VelocityVector vel)
	{
		super(world, pos, dim, spr, vel);
	}

	public boolean isSolid() {return true;}

	public void draw()
	{
		glDisable(GL_TEXTURE_2D);
		glLineWidth(3);
		glBegin(GL_LINES);
		{
			glColor3f(0f, 0f, 0f);
			glVertex2i(getX(), getY());
			glColor4f(0f,0f,0f,0.5f);
			glVertex2d(getPreviousX(), getPreviousY());
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}
	
	protected boolean rightColideType(Entity entity)
	{
		return entity instanceof Platform || entity instanceof Enemy;
	}
	
	protected boolean willColide(Entity entity, VelocityVector checkVel) {
		int x1 = (int) Math.min(getX(), getX() + checkVel.getX());
		int x2 = (int) Math.max(getX(), getX() + checkVel.getX());
		int y1 = (int) Math.min(getY(), getY() + checkVel.getY());
		int y2 = (int) Math.max(getY(), getY() + checkVel.getY());
		
		if((x1 <= entity.getX() + entity.getWidth() && x1 >= entity.getX()) ||
		   (x2 <= entity.getX() + entity.getWidth() && x2 >= entity.getX()) ||
		   (x1 <= entity.getX() && x2 >= entity.getX() + entity.getWidth()) ||
		   (x2 <= entity.getX() && x1 >= entity.getX() + entity.getWidth()))
		{
			if((y1 <= entity.getY() + entity.getHeight() && y1 >= entity.getY()) ||
			   (y2 <= entity.getY() + entity.getHeight() && y2 >= entity.getY()) ||
			   (y1 <= entity.getY() && y2 >= entity.getY() + entity.getHeight()) ||
			   (y2 <= entity.getY() && y1 >= entity.getY() + entity.getHeight()))
			{
				markForDestruction();
				if(entity instanceof Enemy) ((Enemy) entity).prepDamage(5);
				return true;
			}
		}
		
		return false;
	}

	public void destroy()
	{
		Sound.bulletHit.play(world.getGame().getVolume());
		world.add(new Particle(world, new Position(getX()-2, getY()-2), new Dimension(16,16), new AnimatedSprite(AnimatedSprite.shotBlip), 18));
		super.destroy();
	}
}
