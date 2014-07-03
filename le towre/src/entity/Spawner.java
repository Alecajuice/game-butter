package entity;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.Dimension;

import entity.mech.Item;
import world.World;
import kinematics.Position;
import kinematics.VelocityVector;

public class Spawner extends MobileEntity {
	private int kiwiNumber = 0;
	private int kiwiTimer = 1;
	private int kiwiSpeed = 5;
	private int iterateSpeed = 0;
	private int kiwiHealth = 1;
	private boolean itemSpawned = false;
	
	public Spawner(World world, Position pos) {
		super(world, pos);
		this.vel = new VelocityVector();
		this.dim = new Dimension(0,0);
	}
	
	public void update() {
		spawnKiwis();
		//spawnItem();
	}
	
	// KIWI SPAWNING
	private int calcKiwiNumber() {
		kiwiNumber = 0;
		for(Entity entity : world.getEntities()) {
			if(entity.getClass() == Kiwi.class) {kiwiNumber++;}
		}
		return kiwiNumber;
	}
	
	private void spawnKiwis() {
			if(iterateSpeed%600 == 0) {
				kiwiSpeed++;
			}
			if(iterateSpeed%1200 == 0) {
				kiwiHealth++;
				iterateSpeed = 0;
			}
			if(kiwiTimer%(calcKiwiNumber()*2+1)==0) {
				new Kiwi(world, new Position(getHydrants().get(0).getX(),getHydrants().get(0).getY()-4), kiwiHealth, kiwiSpeed);
				kiwiTimer=0;
			}
			kiwiTimer++;
			iterateSpeed++;
	}

	// ITEM SPAWNING
	private void spawnItem() {
		if(!itemSpawned) {
			Position spawnPos = chooseItemSpawn();
			new Item(world, spawnPos, world.getAvatar(), kiwiNumber);
			itemSpawned = true;
		}
		else {
			//TODO Check if item is spawned and change itemSpawned to false if needed;
		}
	}
	
	private Position chooseItemSpawn() {
		double rand = Math.random();
		return new Position(((int) world.getLevelWidth()*rand), 0);
		if(rand < 0.3) {
			return new Position(792, 112);
		}
		else if(rand < 0.6) {
			return new Position(250, 130);
		}
		else {
			return new Position(1100, 200);
		}
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	public List<Entity> getHydrants() {
		List<Entity> hydrants = new ArrayList<Entity>();
		for(Entity entity: world.getEntities()) {
			if(entity.getClass() == Hydrant.class) {
				hydrants.add(entity);
			}
		}
		return hydrants;
	}
}