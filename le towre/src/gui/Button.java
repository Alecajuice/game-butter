package gui;

import graphics.Sprite;
import graphics.StaticSprite;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Dimension;

import kinematics.Position;
public class Button extends Gui
{
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
