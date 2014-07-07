package letowre;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.openal.AL10.*;
import gui.Gui;
import gui.screens.LoadingScreen;
import gui.screens.Screen;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class letowre
{

	public final int SCREEN_WIDTH = 1280; //1280 
		public int getScreenWidth() {return SCREEN_WIDTH;}
	public final int SCREEN_HEIGHT = 720; //720
		public int getScreenHeight() {return SCREEN_HEIGHT;}
	
	private static letowre game;
		public static letowre getGame(){return game;}
	
	private Gui focus;
	private Screen currentScreen;
	
	public static void main(String[] args)
	{
		game = new letowre();
	}
	
	private letowre()
	{
		try
		{
			//Init Display
			Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
			Display.setTitle("SUPER MECHZ 10000");
			Display.create();
			//Init OpenAL
			AL.create();
		}
		catch(Exception e){System.err.println("Catch 1: "); e.printStackTrace();}
		
		//Init OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);

		//Init screen
		currentScreen = new LoadingScreen();
		
		//HERE IT IS, IN ALL IT'S GLORY (btw it's its)
		mainLoop();
		//THAT WAS ANTICLIMACTIC
		
		//Exit
		AL.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	private void mainLoop()
	{
		while(!Display.isCloseRequested())
		{
			update();
			render();
		}
	}
	
	private void update()
	{
		
	}
	
	private void render()
	{
		
	}

	public float getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}
}
