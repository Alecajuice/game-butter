package screens;

import org.lwjgl.input.Keyboard;

import graphics.Button;
import graphics.StaticSprite;
import letowre.letowre;

public class PauseScreen extends Screen {

	private  Button unpauseButton = new Button(Button.unpauseButton);
	private Button backButton = new Button(Button.backbutton);
	private Button settingsButton2 = new Button(Button.settingsButton2);
	private int count = 0;
	

	public PauseScreen(letowre game, StaticSprite background) {
		super(game, background);
	}

	@Override
	public Screen update() {
		
		if(count>10){
			backButton.update();
			unpauseButton.update();
			settingsButton2.update();
		if (backButton.getPrevState() == Button.State.CLICKED || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			return new MainMenu(game);
		}
		if (unpauseButton.getPrevState() == Button.State.CLICKED || Keyboard.isKeyDown(Keyboard.KEY_P)) {
			return new WorldScreen(game, game.world);
		}
		if(settingsButton2.getPrevState() == Button.State.CLICKED || Keyboard.isKeyDown(Keyboard.KEY_O)) {
			return new Settings(game);
		}
		}else count++;
		return this;
	}

	@Override
	public void draw() {
		game.world.draw();
		background.draw(0, 0, game.getScreenWidth()+800, game.getScreenHeight()+400);
		
		backButton.draw();
		unpauseButton.draw();
		settingsButton2.draw();
	}
}
