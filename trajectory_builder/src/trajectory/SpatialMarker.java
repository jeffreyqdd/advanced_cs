package trajectory;

public class SpatialMarker
{
	public double x,y;
	
	public SpatialMarker(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "( " + x + ", " + y + ")";
	}
}
