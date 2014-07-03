package graphics;


public abstract class Sprite
{		
	public abstract void draw(int x, int y, int width, int height);
	public void draw(int x, int y, int width, int height, float alpha){}
}
