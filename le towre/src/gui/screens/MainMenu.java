package gui.screens;

import letowre.letowre;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import graphics.*;
import gui.Button;

public class MainMenu extends Screen
{

	private int count = 0;

	public MainMenu() {
		this(game, StaticSprite.background);
	}

	public MainMenu(letowre game, StaticSprite background) {
		super(game, background);
	}

	public Screen update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && count > 10) {
			return null;
		}
		playButton.update();
		quitButton.update();
		settingsButton.update();
		if (playButton.getPrevState() == Button.State.CLICKED && count > 10) {
			return new LoadingScreen(game);
		}
		else if (quitButton.getPrevState() == Button.State.CLICKED && count > 10) {
			return null;
		}
		else if (settingsButton.getPrevState() == Button.State.CLICKED && count > 10) {
			return new Settings(game);
		}
		count++;
		return this;
	}

	public void draw() {
		background.draw(0, 0, game.getScreenWidth()+800, game.getScreenHeight()+400); //Background is being stupid and doesn't correctly display its size
		playButton.draw();
		quitButton.draw();
		settingsButton.draw();
	}
}



