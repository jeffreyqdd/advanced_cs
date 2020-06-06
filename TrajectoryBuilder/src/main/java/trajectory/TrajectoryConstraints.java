package trajectory;

public class TrajectoryConstraints
{
	public double spacing = 3; //inches
	public double maxAccel = 10; //inch / sec^2
	public double maxVelocity =  50; //inches/ sec;\
	public double turnConstant = 1;
	
	public double weightData = 0.01;
	public double weightSmooth = 1-weightData;
	public double tolerance = 1;
}

