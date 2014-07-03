package kinematics;

public class VelocityVector extends Vector
{
	// units: 1 = 1 pixel / update

	public VelocityVector(double x, double y)
	{
		super(x, y);
	}

	public VelocityVector(Vector sum)
	{
		super(sum.getX(), sum.getY());
	}

	public VelocityVector()
	{
		super(0, 0);
	}

}
