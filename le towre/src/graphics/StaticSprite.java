package graphics;

import java.io.FileInputStream;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.*;

public class StaticSprite extends Sprite
{		
	// World sprites
	public static final StaticSprite dirt = new StaticSprite("tiles/dirt");
	public static final StaticSprite grass = new StaticSprite("tiles/grass");
	public static final StaticSprite hydrant = new StaticSprite("tiles/fire_hydrant");

	// Mech sprites
	public static final StaticSprite mechRight = new StaticSprite("mechs/mechRight");
	public static final StaticSprite mechLeft = new StaticSprite("mechs/mechLeft");
	public static final StaticSprite bullet = new StaticSprite("projectiles/bullet");

	// Menu sprites
	public static final StaticSprite pauseBackground = new StaticSprite("/pausebackgroundv1");
	public final static StaticSprite[] back = {new StaticSprite("Buttons/backpushedv1"), new StaticSprite("Buttons/backunpushedv1")};
	public final static StaticSprite[] pause = {new StaticSprite("Buttons/pausepushedv1"), new StaticSprite("Buttons/pauseunpushedv1")};
	public static final StaticSprite[] unpause = {new StaticSprite("Buttons/unpausepushedv1"), new StaticSprite("Buttons/unpauseunpushedv1")};

	public final static StaticSprite[] play = {new StaticSprite("Buttons/playpushedv1"), new StaticSprite("Buttons/playunpushedv1")};
	public static final StaticSprite[] quit = {new StaticSprite("Buttons/quitpushedv1"), new StaticSprite("Buttons/quitunpushedv1")};
	public static final StaticSprite[] settings = {new StaticSprite("Buttons/settingspushedv1"), new StaticSprite("Buttons/settingsunpushedv1")};
	public static final StaticSprite[] settings2 = {new StaticSprite("Buttons/settingspushedv1"), new StaticSprite("Buttons/settingsunpushedv1")};
	public static final StaticSprite background = new StaticSprite("menu2");
	public static final StaticSprite settingsBackground = new StaticSprite("tiles/dirt"); //TEMPORARY PLACEHOLDER

	// Enemy sprites
	public static final StaticSprite kiwiLeft = new StaticSprite("Kiwi/Left/Kiwi_left_standing");
	public static final StaticSprite kiwiRight = new StaticSprite("Kiwi/Right/Kiwi_right_standing");
	public static final StaticSprite penguinLeft = new StaticSprite("Penguin/penguin_left_gliding");
	public static final StaticSprite penguinRight = new StaticSprite("Penguin/penguin_right_gliding.png");
	
	// Item sprite
	public static final StaticSprite healthItem = new StaticSprite("Items/health");

	private final String fileType;
	private final String fileName;
	private Texture tex;

	public StaticSprite(StaticSprite spr)
	{
		this(spr.fileType, spr.fileName);
	}

	private StaticSprite(String fileName)
	{
		this("png", fileName);
	}

	private StaticSprite(String fileType, String fileName)
	{
		this.fileType = fileType;
		this.fileName = fileName;
	}

	public void draw(int x, int y, int width, int height)
	{
		if(tex == null)
		{
			loadTexture();
		}
		glEnable(GL_TEXTURE_2D);
		glColor3f(1f, 1f, 1f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		tex.bind();
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2i(x, y);

			glTexCoord2f(1, 0);
			glVertex2i(x + width, y);

			glTexCoord2f(1, 1);
			glVertex2i(x + width, y + height);

			glTexCoord2f(0, 1);
			glVertex2i(x, y + height);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}

	public void loadTexture()
	{
		try
		{
			tex = TextureLoader.getTexture(fileType, new FileInputStream("res/img/" + fileName + "." + fileType), GL_NEAREST);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
