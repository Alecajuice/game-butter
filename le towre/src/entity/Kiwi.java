package entity;

import graphics.Sprite;
import graphics.StaticSprite;
import kinematics.Position;
import kinematics.VelocityVector;

import org.lwjgl.util.Dimension;

import world.World;

public class Kiwi extends Enemy {
	
	//every AI state has a number, switches between
	private int lastAI;
	//this is for when the kiwi is above the human, it basically makes it go a certain direction to get off of whatever platform is making it 
	//above the avatar
	private boolean leftRight = true;
	//this is for when the kiwi is above the human, when it changes Y (signaling that it has jumped off the platform). 
	//it will then check for the mech pos again
	//will make better when we have better collision
	private int lastY;

	public Kiwi(World world, Position pos,  int health, int speed) {
		super(world, pos, new Dimension(50, 32), StaticSprite.kiwiRight, new VelocityVector(0,0), health);
		this.speed = speed + Math.random()*2 -1;
		jumpSpeed = 20 + Math.random()*3 - 1.5;
		lastAI = 0; 
	}
	
	public void update() {
		ai();
		super.update();
	}
	protected void ai() {
		// If kiwi is below the avatar
		ifKiwiBelowAI();
		// if kiwi is above enemy
		ifKiwiAboveAI();
		// If kiwi ~ same height as avatar
		ifKiwiSameAI();

	}
	
	public void die()
	{
		world.getAvatar().changeScore(10);
		super.die();
	}
	
	public void interact(Entity entity)
	{
		if(entity instanceof Avatar && Math.random()>0.9) ((Avatar) entity).damage(1);
	}
	
	public void reset(int aiType) {
		if(!(lastAI == aiType)) {
			setXSpeed(0);
		}
	}
	
	private void ifKiwiBelowAI() {
		if(getY()-getHeight() > world.getAvatarY()+world.getAvatarHeight()+36) {
			reset(0);
			if(jumped == false) {
				jumped = true;
				setYSpeed(-jumpSpeed);
			}
			if(getX() > world.getAvatarX()+world.getAvatarWidth()) {
				setXSpeed(-speed);
				setSprite(StaticSprite.kiwiLeft);
			}
			else if(getX()+dim.getWidth() < world.getAvatarX()) {
				setXSpeed(speed);
				setSprite(StaticSprite.kiwiRight);
			}
			else {
				setXSpeed(0);
			}
			lastAI = 0;
		}
	}
	private void ifKiwiSameAI() {
		if(getY()+getHeight() > world.getAvatarY() - 36 && getY() < world.getAvatarY() + world.getAvatarHeight()+36) {
			reset(2);
			if(getX() > world.getAvatarX()+world.getAvatarWidth()) {
				setXSpeed(-speed);
				setSprite(StaticSprite.kiwiLeft);
			}
			else if(getX()+dim.getWidth() < world.getAvatarX()) {
				setXSpeed(speed);
				setSprite(StaticSprite.kiwiRight);
			}
			else {
				setXSpeed(0);
			}	
			lastAI = 2;
		}
	}
	private void ifKiwiAboveAI() {
		if(getY()+getWidth() < world.getAvatarY()-36) {
			reset(1);
			if(lastAI != 1) {
				if(getX() > world.getAvatarX()+world.getAvatarWidth()) {
					leftRight = false;
				}
				else if(getX()+dim.getWidth() < world.getAvatarX()) {
					leftRight = true;
				}
			}
			else if(lastY!=getY()) {
				if(getX() > world.getAvatarX()+world.getAvatarWidth()) {
					leftRight = false;
				}
				else if(getX()+dim.getWidth() < world.getAvatarX()) {
					leftRight = true;
				}
			}
			if(leftRight) {
				setXSpeed(speed);
				setSprite(StaticSprite.kiwiRight);

			}
			else {
				setXSpeed(-speed);
				setSprite(StaticSprite.kiwiLeft);
			}
			lastY = getY();
			lastAI = 1;
		}
	}
}
