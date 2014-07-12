package resource;

import javax.swing.SwingWorker;

public class ResourceLoader extends SwingWorker<Void, Void>
{
	private Resource r;
	
	public ResourceLoader(Resource r)
	{
		this.r = r;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		r.loadResource();
		donee();
		return null;
	}
	
	public void donee()
	{
		System.out.println("Loaded resource " + r.getPath());
	}
}
