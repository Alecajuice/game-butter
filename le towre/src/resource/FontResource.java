package resource;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FontResource extends Resource
{
	private UnicodeFont font;
		public UnicodeFont getFont(){return font;}
	private Font awtFont;
	private int size;
	private boolean bold;
	private boolean italic;
		
	public FontResource(String filePath, String fileType, int size, boolean bold, boolean italic)
	{
		super(filePath, "ttf");
		this.fileName = filePath;
		this.size = size;
		this.bold = bold;
		this.italic = italic;
	}
	
	public FontResource deriveFont(int size, boolean bold, boolean italic)
	{
		return new FontResource(this.fileName, this.fileType, size, bold, italic);
	}
	
	@Override
	protected void loadResource()
	{
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream(this.getPath());
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			font = new UnicodeFont(awtFont, size, bold, italic);
		} catch (FontFormatException e) {} catch (IOException e) {}
	}

}
