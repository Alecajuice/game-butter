package resource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.test.SysTest;

public class TextResource extends Resource
{
	private String[] text;
		public String[] getText(){return this.text;}
	public TextResource(String filePath, String fileType)
	{
		super("texts/" + filePath, fileType);
		this.fileName = filePath;
	}

	@Override
	protected void loadResource()
	{
		try {
			Scanner s = new Scanner(this);
			ArrayList list = new ArrayList<String>();
			while(s.hasNextLine())
			{
				String line = s.nextLine();
				list.add(line);
			}
			text = (String[])list.toArray(new String[list.size()]);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
	}
	
	@Override
	public void register()
	{
		Resource.resources.put("text-" + fileName, this);
	}
	
	public String getKey(String fileName)
	{
		return "text-" + fileName;
	}
	
	public String getKey()
	{
		return "text-" + fileName;
	}
}
