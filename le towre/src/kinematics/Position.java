package kinematics;
import java.awt.Point;

import kinematics.Position;

public class Position extends Point
{
	public Position()
	{
		super(0, 0);
	}
	
	public Position(int x, int y)
	{
		super(x, y);
	}
	
	public Position(Position pos)
	{
		super(pos.x, pos.y);
	}
	
	public Position push(VelocityVector v)
	{
		return new Position(x + (int)v.getX(), y + (int)v.getY());
	}
	
	public boolean equals(Object object)
	{
		if(object.getClass() != getClass()) return false;
		Position pos = (Position) object;
		return (x == pos.getX()) && (y == pos.getY());
	}
	
	public int hashCode()
	{
		return ((Integer)x).hashCode()^((Integer)y).hashCode();
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	public boolean isInArea(Position pos, int width, int height) {
		return (x >= pos.getX() && x <= pos.getX()+width && y >= pos.getY() && y <= pos.getY()+height) ? true : false;
	}
	public boolean isInArea(int x, int y, int width, int height) {
		return (this.x >= x && this.x <= x+width && this.y >= y && this.y <= y+height) ? true : false;
	}
}
