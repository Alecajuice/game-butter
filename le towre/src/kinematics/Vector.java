package kinematics;

public class Vector
{
	private double x; 
		public double getX(){return x;} 
		public void setX(double i) {x = i;}
	private double y; 
		public double getY(){return y;} 
		public void setY(double i) {y = i;}
	
	public Vector()
	{
		this(0, 0);
	}
	
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector(Position pos1, Position pos2)
	{
		this(pos2.getX() - pos1.getX(), pos2.getY() - pos1.getY());
	}
	
	public Vector add(Vector other)
	{
		return new Vector(this.x + other.getX(), this.y + other.getY());
	}
	
	public static Vector newRadialVector(double angle, double magnitude)
	{
		return new Vector(Math.cos(angle)*magnitude, Math.sin(angle)*magnitude);
	}
	
	public Vector scale(double scalar)
	{
		return new Vector(scalar*this.x, scalar*this.y);
	}
	
	public boolean equals(Object object)
	{
		if(object.getClass() != getClass()) return false;
		Vector vec = (Vector) object;
		return (x == vec.x) && (y == vec.y);
	}
	
	public int hashCode()
	{
		return ((Double)x).hashCode()^((Double)y).hashCode();
	}
	
	public String toString()
	{
		return "[" + x + ", " + y + "]";
	}
	public Vector setMag(double newMag)
	{
		//System.out.println(this + ", " + newMag + ", " + getMag() + ", " + newMag/getMag() + ", " + scale(newMag/getMag()) + ", " + scale(newMag/getMag()).getMag());
		return scale(newMag/getMag());
	}
	
	public double getMag()
	{
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}
}
