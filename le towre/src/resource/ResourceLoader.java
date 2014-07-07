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
		return null;
	}
	
	public void done()
	{
		System.out.println("Loaded resource " + r.getPath());
	}
}
