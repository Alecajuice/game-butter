package graphics;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Dimension;
import kinematics.Position;
public class Button {

	public static final Button playButton = new Button(StaticSprite.play, new Position(800, 450), 256, 64);
	public static final Button quitButton = new Button(StaticSprite.quit, new Position(800, 516), 128, 32);
	public static final Button settingsButton = new Button(StaticSprite.settings, new Position(928, 516), 128, 32);
	public static final Button backbutton = new Button(StaticSprite.back, new Position(10, 10), 64, 32);
	public static final Button pauseButton = new Button(StaticSprite.pause, new Position(1250, 10), 16, 16);
	public static final Button unpauseButton = new Button(StaticSprite.unpause, new Position(576, 296), 128, 128);
	public static final Button settingsButton2 = new Button(StaticSprite.settings2, new Position(576, 424), 128, 30);

	private Sprite[] sprite;
	private Position pos;
	private int width, height;

	public enum State {

		UNCLICKED(1), CLICKED(0);

		private int value;
		private State(int value) {
			this.value = value;
		}
		public int getValue() { return value; }
	}

	private State state;
	public State getState() { return state; }
	private State prevState;
	public State getPrevState() { return prevState; }

	public Sprite[] getSprite() { return sprite; }
	public Position getPos() { return pos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public Button(Button button) {
		this(button.getSprite(), button.getPos(), button.getWidth(), button.getHeight());
	}
	public Button(Sprite[] sprite, Position pos, int width, int height) {
		this(sprite, pos, width, height, true);
	}

	public Button(Sprite[] sprite, Position pos, int width, int height, boolean able) {
		this.sprite = sprite;
		this.pos = pos;
		this.width = width;
		this.height = height;
		state = State.UNCLICKED;

	}
	public boolean isClicked() {
		Position mousePos = new Position(Mouse.getX(), 720-Mouse.getY());
		return (mousePos.isInArea(pos, width, height) && Mouse.isButtonDown(0));
	}

	public void draw() {
		sprite[state.getValue()].draw((int)pos.getX(), (int)pos.getY(), width, height);
	}

	public void update() {
		prevState = state;
		if (isClicked()) {
			state = State.CLICKED;
		} else {
			state = State.UNCLICKED;
		}
	}
}
