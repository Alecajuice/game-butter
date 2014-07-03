package entity;

import graphics.Sprite;
import graphics.StaticSprite;
import kinematics.Position;
import kinematics.VelocityVector;

import org.lwjgl.util.Dimension;

import world.World;

public class KamperKiwi extends Enemy {
	public KamperKiwi(World world, Position pos, int health) {
		super(world, pos, new Dimension(50, 32), StaticSprite.kiwiRight, new VelocityVector(0,0), health);
		speed = 3;
		jumpSpeed = 0;
	}
	
	public void update() {
		ai();
		super.update();
	}
	
	//TODO Don't fall off the platform.
	@Override
	protected void ai() {
		// If kiwi ~ same height as avatar
		if(Math.abs((getY()-getHeight()) - (world.getAvatarY() - world.getAvatarHeight())) < 100) {
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
		}
		else {
			setXSpeed(0);
			//jump();
		}
	}
	
	private void jump() {
		setYSpeed(-jumpSpeed);
	}
}
