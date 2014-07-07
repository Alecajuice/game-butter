package resource;

import java.io.File;

public class Resource extends File
{

	public Resource(String filePath)
	{
		super("/assets/" + filePath);
	}
}
