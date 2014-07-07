package gui.screens;

import java.util.ArrayList;
import java.util.List;

import gui.Button;
import gui.Gui;

public abstract class Screen extends Gui
{
	protected List<Button> buttonList = new ArrayList<Button>();
	
	public abstract Screen update();
	
	public void draw()
	{
		for (Button b : buttonList)
		{
			b.draw();
		}
	}
}
