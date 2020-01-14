public class ProjectileCalculator
{

	public static final double accel_grav = 9.8; //m/s^2
	public static final double coeff_drag = 0.43; //value
	public static final double ball_mass = 0.141748; //kg
	public static final double density_air = 1.225; //density of air
	public static final double cross_area = 0.02482866647; //in m^2
	public static final double drag = (coeff_drag * density_air * coeff_drag) * 0.5;
	public static final double alpha = Math.PI / 4;
	public static final double 	height_init = 0;
	//param x: meters, v: meters/s
	public static double heightAt(double x, double v)
	{
		double t = (-ball_mass/drag)* (Math.log( ball_mass * v * Math.cos(alpha) / drag - x )
									-  Math.log( ball_mass * v * Math.cos(alpha) / drag)  );
		
		System.out.println(t);
		double h = (-ball_mass * accel_grav * t / drag) +
				ball_mass/drag * (v * Math.sin(alpha) + ball_mass * accel_grav / drag) *
						(1 - Math.pow(Math.E, -drag*t/ball_mass)) + height_init;
		return h;
	}
	
	
	
	
	public static void main(String[] args)
	{
		System.out.println(heightAt(5,10));
	}
}
