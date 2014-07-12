package gui.screens;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import main.SuperMechz10000;
import graphics.*;

public class Settings extends Screen {
	
	public Settings(SuperMechz10000 game) {
		this(game, StaticSprite.settingsBackground);
	}

	public Settings(SuperMechz10000 game, Sprite background) {
		super(game, background);
	}

	public Screen update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			game.setVolume(game.getVolume() + 1);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			game.setVolume(game.getVolume() - 1);
		}


		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
			return new MainMenu(game);
		return this;
	}

	public void draw() {
		background.draw(0, 0, game.getScreenWidth(), game.getScreenHeight());
		GameFont.defaultFont40pt.draw(128, 128, 24, "Volume: " + game.getVolume(), new Color(0.8f, 0.8f, 1f, 0.9f));
	}
}
