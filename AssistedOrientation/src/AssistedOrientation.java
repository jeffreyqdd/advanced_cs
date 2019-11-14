import java.util.concurrent.TimeUnit;

public class AssistedOrientation {
	
	double distLeft;
	double distRight;
	
	public PIDController PIDLeft;
	public PIDController PIDRight;
	
	private static double kp = 0.7, ki = 0.0, kd = 0;
	private static final double DIST = 10;
	
	public AssistedOrientation() {

		
		PIDLeft = new PIDController(DIST, kp, ki, kd);
		PIDRight = new PIDController(DIST, kp, ki, kd);
		
		distLeft = 0;
		distRight = 400;
		
	}
	
	
	public static void main(String[] args) throws InterruptedException
	{

		
		AssistedOrientation driver = new AssistedOrientation();
		
		for(int i = 0; ; i++)
		{
			Double leftChange = driver.PIDLeft.update(System.currentTimeMillis(), driver.distLeft);
			Double rightChange = driver.PIDRight.update(System.currentTimeMillis(), driver.distRight);
			
			if(leftChange != null)
				driver.distLeft += leftChange;
			
			if(rightChange != null)
				driver.distRight += rightChange;
			
			System.out.println(driver.distLeft + ", " + driver.distRight);
			
			Thread.sleep(100);
		}
	}
	
	
}
