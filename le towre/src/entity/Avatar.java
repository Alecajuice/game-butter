package entity;

import entity.mech.Item;
import graphics.AnimatedSprite;
import graphics.Sprite;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Dimension;
import org.newdawn.slick.Color;

import particles.Particle;
import audio.Sound;
import screens.GameOverScreen;
import screens.MainMenu;
import graphics.StaticSprite;
import world.World;
import kinematics.*;

public class Avatar extends Mob
{
	//Base values
	private final int BASE_SPEED = 10;
	
	//Perhaps these should be in item/weapon
	private final int BASE_SHOOT_COOL_DOWN = 10;
	
	private final double BASE_JUMP_SPEED = 20;
	
	//Actual values, initialized to base values an be changed through use of items
	private int speed;
	private int shootCoolDown;
	private double jumpSpeed;
	private boolean isShooting = false;
	private int scoreCount = 0;
	
	private Particle smoke;
	
	//The current relode time, when reaches 0, can be used again
	private int reloadTime = 0;
	private int heat = 0;
	
	private int score = 0;
		public int getScore() {return score;}
		public void changeScore(int amount) {score+=amount; scoreTracker.updateValue(score);}
	
	private ValueEntity scoreTracker = new ValueEntity(world, new Position(30, 60), new Dimension(32, 32), score, Color.black);

	private BarValueEntity healthBar = new BarValueEntity(world, new Position(20, 20), new Dimension(256, 32), health, MAX_HEALTH, new Color(0f, 0.8f, 0f), new Color(0.8f, 0f, 0f));

	public String direction = "right";
	
	public Avatar(World world, Position pos)
	{
		super(world, pos, new Dimension(64, 64),  StaticSprite.mechRight, new VelocityVector(), 500);
		smoke = new Particle(world, new Position(500, 500), new Dimension(20, 50), AnimatedSprite.smoke, -1);
		speed = BASE_SPEED;
		shootCoolDown = BASE_SHOOT_COOL_DOWN;
		jumpSpeed = BASE_JUMP_SPEED;
	}

	public void update()
	{
		if(Mouse.isButtonDown(0))
		{
			if(reloadTime<=0)
			{
				shoot();
				heat+=10;
				reloadTime = shootCoolDown;
			}
			if(!isShooting)
			{
				Sound.minigunStart.play(world.getGame().getVolume());
				isShooting = true;
			}
			else
			{
				if(!Sound.minigunStart.playing && !Sound.minigunLoop.playing)
				{
					Sound.minigunLoop.play(world.getGame().getVolume());
				}
				if(!Sound.minigunStart.playing)
				{
					Sound.shoot.play(world.getGame().getVolume());
				}
			}
		}
		else
		{
			if(heat>=5)heat-=5;
			if(isShooting)
			{
				Sound.minigunLoop.stop();
				Sound.minigunEnd.play();
			}
			isShooting = false;
		}
		if(reloadTime>0)
		{
			reloadTime--;
		}
		
		
		if(scoreCount >= 2)
		{
			changeScore(1);
			scoreCount = 0;
		}
		else
		{
			scoreCount++;
		}


		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			setXSpeed(-speed);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			setXSpeed(speed);
		}
		else
		{
			setXSpeed(0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_K))
		{
			damage(1);
		}
		
		if(((Keyboard.isKeyDown(Keyboard.KEY_UP)) || Keyboard.isKeyDown(Keyboard.KEY_W)) && (jumped == false))
		{
			jumped = true;
			setYSpeed(-jumpSpeed);
			Sound.jump.play(world.getGame().getVolume());
		}
		if(Mouse.getX() > getX() + (getWidth()/2))
		{
			setSprite(new StaticSprite(StaticSprite.mechRight));
			direction = "right";
		}
		else
		{
			setSprite(new StaticSprite(StaticSprite.mechLeft));
			direction = "left";
		}
		super.update();
		if(direction.equals("left"))
		{
			smoke.setX(getX() - 5);
			smoke.setY(getY() - 10);
		}
		else
		{
			smoke.setX(getX() + getWidth() - 8);
			smoke.setY(getY() - 10);
		}
		smoke.setAlpha((float)heat/240f);
		if(heat/240 > 1) smoke.setAlpha(1f);
	}
	
	protected void die()
	{
		world.getGame().setScreen(new GameOverScreen(world.getGame()));
		Sound.minigunLoop.stop();
		Sound.minigunEnd.play();
	}
	
	public void damage(int amount)
	{
		super.damage(amount);
		Sound.hurt.play(world.getGame().getVolume());
		healthBar.updateValue(health);
	}

	public void shoot()
	{
		if(direction.equals("left"))
		{
			new AvatarBullet(world, new Position(getX(), getY() + 25),
					 new Position((int)(world.getGame().getMouseX() + Math.random()*heat/2 - heat/4),
							      (int)(world.getGame().getMouseY() + Math.random()*heat/2 - heat/4)));
		}
		else
		{
			new AvatarBullet(world, new Position(getX() + getWidth() - 3, getY() + 25),
					 new Position((int)(world.getGame().getMouseX() + Math.random()*heat/2 - heat/4),
							      (int)(world.getGame().getMouseY() + Math.random()*heat/2 - heat/4)));
		}
	}
	
	public void restoreHP(int restoreAmt) {
		health = (int) Math.min(health + 20*Math.random(), 100);
		healthBar.updateValue(health);
	}
	
	protected boolean rightColideType(Entity entity)
	{
		return super.rightColideType(entity) || entity instanceof Item;
	}
	
	public void interact(Entity entity)
	{
		if(entity instanceof Item)
		{
			restoreHP(((Item)entity).restoreAmt);
			entity.destroy();
		}
		super.interact(entity);
	}
}
