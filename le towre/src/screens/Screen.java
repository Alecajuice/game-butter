package screens;

import letowre.letowre;
import graphics.*;

public abstract class Screen {
	
	protected Sprite background;
	protected letowre game;
	
	protected Screen screenState;
		public Screen getScreenState() { return screenState; }
		public void setScreenState(Screen screen) { screenState = screen; }
	
	public Screen(letowre game, Sprite background) {
		this.background = background;
		this.game = game;
	}
	
	public abstract Screen update();
	
	public abstract void draw();
}
