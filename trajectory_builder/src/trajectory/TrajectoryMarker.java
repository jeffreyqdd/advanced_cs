package trajectory;

public class TrajectoryMarker
{
	public double x,y, velocity;
	
	public TrajectoryMarker(double x, double y, double velocity) {
		this.x = x;
		this.y = y;
		this.velocity = velocity;
	}
	
	@Override
	public String toString() {
		return "( " + x + ", " + y + ", " + ") at " + velocity + "inch/s";
	}
}
