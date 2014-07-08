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
import resource.Resource;
import resource.TextResource;
import graphics.Sprite;
import graphics.StaticSprite;


public class LoadingScreen extends Screen
{
	private static TextResource loadingTexts = new TextResource("loadingTexts", "txt");
	String loadText = "";

	private static FontResource font = new FontResource("square", "ttf", Color.white, 20, false, true);;
	private boolean antiAlias = true;
	private static final int loadingTime = 90; 
	private static int randNum;
	private int count = 0;
	private int prevCount = count;
//	private boolean done = false;

	public LoadingScreen()
	{
		super();
		
		font.load();
		Resource.resources.remove(font.getKey());
		font.register();
		
		loadingTexts.load();
		Resource.resources.remove(loadingTexts.getKey());
		loadingTexts.register();
		
		for (Object key : Resource.resources.keySet())
		{
			((Resource)Resource.resources.get((String)key)).load();
		}
		
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
			if (font.getMetrics().stringWidth(loadText) > letowre.getGame().getScreenWidth())
			{
				System.out.println("hi");
				boolean breakfound = false;
				int prevSpace = 0;
				while(!breakfound)
				{
					if(loadText.indexOf(" ", prevSpace + 1) > loadText.length()/2)
					{
						breakfound = true;
						loadText = loadText.substring(0, loadText.indexOf(" ", prevSpace + 1) - 2) + "/n" + loadText.substring(loadText.indexOf(" ", prevSpace + 1) - 1);
						System.out.println(loadText);
					}
					else if (loadText.indexOf(" ") == -1)
					{
						breakfound = true;
						System.out.println("fk");
					}
					else
					{
						prevSpace = loadText.indexOf(" ", prevSpace + 1);
					}
				}
			}
		}
		count++;
		boolean done = true;
		for (Object key : Resource.resources.keySet())
		{
			if(!((Resource)Resource.resources.get((String)key)).loaded)
			{
				done = false;
			}
		}
		if (done)
		{
			return new MainMenu();
		}
		return this;
	}

	public void draw()
	{
		if(loadText.indexOf("/n") != -1)
		{
			String first = loadText.substring(0, loadText.indexOf("/n"));
			String second = loadText.substring(loadText.indexOf("/n") + 2);
			font.drawString((letowre.getGame().getScreenWidth()/2)-(font.getMetrics().stringWidth(first)/2), 570, first); //to be changed to move according to length of string
			font.drawString((letowre.getGame().getScreenWidth()/2)-(font.getMetrics().stringWidth(second)/2), 600, second); //to be changed to move according to length of string
		}
		else
		{
			try {
				font.drawString((letowre.getGame().getScreenWidth()/2)-(font.getMetrics().stringWidth(loadText)/2), 600, loadText); //to be changed to move according to length of string
			} catch(NullPointerException e){}
		}
	}
	
	public void reset()
	{
		count = 0;
	}
}
