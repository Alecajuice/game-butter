package resource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public abstract class Resource extends File
{
    public static final Map resources = new HashMap<String, Resource>();
	private ResourceLoader loader = new ResourceLoader(this);
	
	protected String fileType;
//		public String getFileType() {return fileType;}
//		public void setFileType(String fileType) {this.fileType = fileType;}
	protected String fileName;
	public boolean loaded = false;

	public Resource(String filePath, String fileType)
	{
		super("assets/" + filePath + "." + fileType);
		this.fileType = fileType;
		this.register();
	}
	
	protected abstract void loadResource();
	
	public abstract void register();
	
	public void load()
	{
		loader.execute();
	}
}
