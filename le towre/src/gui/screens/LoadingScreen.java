package gui.screens;

import static org.lwjgl.opengl.GL11.*;

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

import resource.TextResource;
import graphics.Sprite;
import graphics.StaticSprite;


public class LoadingScreen extends Screen
{
	private static TextResource loadingTexts = new TextResource("loadingText", "txt");
	String loadText = "";

	private TrueTypeFont font;
	private boolean antiAlias = true;
	private static final int loadingTime = 90; 
	private static int randNum;
	private int count = 0;
	private int prevCount = count;
	private boolean done = false;

	public LoadingScreen()
	{
		super(); 
		
		
		randNum = (int)Math.round(Math.random()*(loadingTexts.getText().length-1));
		loadText = loadingTexts.getText()[randNum];
		
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("/res/fonts/AlexandriaFLF-Italic.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(20f); //set font size
			font = new TrueTypeFont(awtFont2, false);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		Load loading = new Load();
		loading.execute();
	}

	public Screen update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			return new MainMenu();
		if (count % 90 == 0 && count != prevCount) {
			randNum = (int)Math.round(Math.random()*(loadingTexts.getText().length-1));
			prevCount = count;
			loadText = loadingTexts.getText()[randNum];
		}
		count++;
		if (done)
			return new WorldScreen();

		return this;
	}

	public void draw() {
		font.drawString((letowre.getGame().getScreenWidth()/2)-(loadText.length()/2*10), 600, loadText); //to be changed to move according to length of string

	}
	
	public void reset() {
		count = 0;
	}

	class Load extends SwingWorker<Void, Void>
	{

		@Override
		protected Void doInBackground() throws Exception
		{
			GameFont.loadFonts();
			done = true;
			return null;
		}
		
		public void done()
		{
			System.out.println("Fully loaded and operational.");
		}
	}
}
