package gui.screens;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

import javax.swing.SwingWorker;

import letowre.letowre;

import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import resource.FontResource;
import resource.TextResource;
import graphics.Sprite;
import graphics.StaticSprite;


public class LoadingScreen extends Screen
{
	private static TextResource loadingTexts = new TextResource("loadingTexts", "txt");
	String loadText = "";

	private FontResource font;
	private boolean antiAlias = true;
	private static final int loadingTime = 90; 
	private static int randNum;
	private int count = 0;
	private int prevCount = count;
	private boolean done = false;

	public LoadingScreen()
	{
		super();
		
		font = new FontResource("square", "ttf", Color.white, 20, false, true);
		
//		randNum = (int)Math.round(Math.random()*(loadingTexts.getText().length-1));
//		loadText = loadingTexts.getText()[randNum];
	}

	public Screen update()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			return new MainMenu();
		if (count % 90 == 0 && count != prevCount) {
			randNum = (int)Math.round(Math.random()*(loadingTexts.getText().length-1));
			prevCount = count;
			loadText = loadingTexts.getText()[randNum];
		}
		count++;
//		if (done)
//			return new WorldScreen();

		return this;
	}

	public void draw()
	{
		try {
			font.drawString((letowre.getGame().getScreenWidth()/2)-(font.getMetrics().stringWidth(loadText)/2), 600, loadText); //to be changed to move according to length of string
		} catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
	public void reset()
	{
		count = 0;
	}
}
