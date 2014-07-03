package kinematics;
import kinematics.*;

public class Line {
	Position p1, p2;
	
	public Line(Position p1, Position p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public int getY() {
		return (int)p1.getY();
	}
	
	public int getX() {
		return (int)p1.getX();
	}
	
	public int getHoriCollision(int yint, Position p1, Position p2) {
		if(p2.getY() - p1.getY() == 0)
			return 4321;
		double slope = (p2.getX() - p1.getX())/(p2.getY() - p1.getY());
		double delta = yint - p1.getY();
		double collideAt = (delta * slope) + p1.getX();
		return((int) collideAt);
	}
	
	public int getVertCollision(int xint, Position p1, Position p2) {
		if(p2.getX() - p1.getX() == 0)
			return 4321;
		double slope = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		double delta = xint - p1.getX();
		double collideAt = (delta * slope) + p1.getY();
		return((int) collideAt);
	}
}
