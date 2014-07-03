package screens;

import letowre.letowre;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import audio.Sound;
import graphics.Button;
import graphics.GameFont;
import graphics.Sprite;
import graphics.StaticSprite;

public class GameOverScreen extends Screen {
	
	private boolean hasPlayed = false;
	private Button playButton = new Button(Button.playButton);
	
	public GameOverScreen(letowre game) {
		super(game, new StaticSprite(StaticSprite.kiwiRight));
		// TODO Auto-generated constructor stub
	}

	public Screen update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
			return new MainMenu(game);
		
		if(!hasPlayed)
		{
			Sound.gameOver.play(game.getVolume());
			hasPlayed = true;
		}
		
		playButton.update();

		if (playButton.getPrevState() == Button.State.CLICKED) {
			return new LoadingScreen(game);
		}
		
		return this;
	}

	public void draw() {
		background.draw(100, 100, 256, 128);
		GameFont.defaultFont40pt.draw(256, 256, 24, "GAME OVER", new Color(0.6f, 0f, 0f));
		GameFont.defaultFont20pt.draw(256, 300, 24, "(Press escape to return to Main Menu)", new Color(0.6f, 0f, 0f));
	}

}
