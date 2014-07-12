package resource;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JComponent;

import letowre.letowre;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class FontResource extends Resource
{
	private UnicodeFont font;
		public UnicodeFont getFont(){return font;}
	private Font awtFont;
	private Color color;
	private int size;
	private boolean bold;
	private boolean italic;
	private FontMetrics metrics;
		public FontMetrics getMetrics(){return metrics;}
		
	public FontResource(String filePath, String fileType)
	{
		this(filePath, fileType, Color.black, 20, false, false);
	}
	
	public FontResource(String filePath, String fileType, Color color, int size, boolean bold, boolean italic)
	{
		super("fonts/" + filePath, "ttf");
		this.fileName = filePath;
		this.color = color;
		this.size = size;
		this.bold = bold;
		this.italic = italic;
	}
	
	public FontResource deriveFont(Color color, int size, boolean bold, boolean italic)
	{
		return new FontResource(this.fileName, this.fileType, color, size, bold, italic);
	}
	
	@Override
	protected void loadResource()
	{
		if(!loaded)
		{
			try {
				letowre.getDrawable().makeCurrent();
			} catch (LWJGLException e) {}
			try {
				InputStream inputStream	= ResourceLoader.getResourceAsStream(this.getPath());
				awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(size);
				font = new UnicodeFont(awtFont, size, bold, italic);
			} catch (FontFormatException e) {} catch (IOException e) {e.printStackTrace();}
			font.getEffects().add(new ColorEffect(color));
			font.addAsciiGlyphs();
			try {
				font.loadGlyphs();
			} catch (SlickException e) {}
			this.metrics = new Canvas().getFontMetrics(awtFont.deriveFont((float)size));
			loaded = true;
		}
	}

	public void drawString(int x, int y, String text)
	{
		font.drawString(x, y, text);
	}
	
	@Override
	public void register()
	{
		Resource.resources.put("font-" + fileName + color + size + bold + italic, this);
		System.out.println("Registered resource font-" + fileName + color + size + bold + italic);
	}
	
	public static String getKey(String fileName, Color color, int size, boolean bold, boolean italic)
	{
		return "font-" + fileName + color + size + bold + italic;
	}
	
	public String getKey()
	{
		return "font-" + fileName + color + size + bold + italic;
	}
}