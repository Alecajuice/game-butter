package resource;

import java.io.File;

public abstract class Resource extends File
{
	private ResourceLoader loader = new ResourceLoader(this);
	
	protected String fileType;
//		public String getFileType() {return fileType;}
//		public void setFileType(String fileType) {this.fileType = fileType;}
	protected String fileName;

	public Resource(String filePath, String fileType)
	{
		super("/assets/" + filePath + "." + fileType);
		this.fileType = fileType;
		loader.execute();
	}
	
	protected abstract void loadResource();
}
