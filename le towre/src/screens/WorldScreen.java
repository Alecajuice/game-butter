package screens;

import graphics.Button;
import org.lwjgl.input.Keyboard;

import world.World;
import graphics.Button;
import graphics.Sprite;
import graphics.StaticSprite;
import letowre.letowre;

public class WorldScreen extends Screen {

	private World world;
	private  Button pauseButton = new Button(Button.pauseButton);
	private int count = 0;

	public WorldScreen(letowre game) {
		this(game, new World(game));
	}

	public WorldScreen(letowre game, World world) {
		super(game, new StaticSprite(StaticSprite.bullet));
		this.world = world;
	}

	public Screen update() {
		if (count > 10) {
		pauseButton.update();
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) { 
			return new MainMenu(game);
		}
		
		if (pauseButton.getState() == Button.State.CLICKED || Keyboard.isKeyDown(Keyboard.KEY_P)) {
			game.world = world;
			return new PauseScreen(game, new StaticSprite(StaticSprite.pauseBackground));
		}

		world.update();
		} else count++;
		return this;
	}

	public void draw() {
		world.draw();
		pauseButton.draw();
	}

}
