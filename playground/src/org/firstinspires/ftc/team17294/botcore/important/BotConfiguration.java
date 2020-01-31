package org.firstinspires.ftc.team17294.botcore.important;

public final class BotConfiguration
{
	
	
	public static final class Kinematic
	{
		//=====================================================
		//motor information
		//may need to change based off newer bindings, gearboxes, or motors.
		//=====================================================
		//motor names
		public static final String motor1 = "top left drive";
		public static final String motor2 = "top right drive";
		public static final String motor3 = "bottom right drive";
		public static final String motor4 = "bottom left drive";
		
		//motor values
		public static final int encoderTicksPerRev = 480;
		public static final int revolutionsPerMinute = 300;
		
		//wheel values
		public static final double wheelRadius = 0.048;
		
		//kinematic values
		public static final double lengthBetweenFrontWheels = 0.395;
		public static final double lengthBetweenFrontAndRearWheels = 0.21;
		
		
		//=====================================================
		//preset values:
		//DO NOT TOUCH WITHOUT GOOD REASON
		//=====================================================
		
		//how far sideways in one wheel rotation compared to forward
		public static final double slideRatio = 1.0;
		public static final double rotationRatio = 1.0;
		public static final double linearAccel = 0.5; //m/s^2
		public static final double rotationAccel = 0.5; //radian/s^2
		
		//=====================================================
		//pre-calculated values;
		//DO NOT TOUCH WITHOUT GOOD REASON
		//=====================================================
		public static final double maxVel = ((double) revolutionsPerMinute / 60.0) * (wheelRadius * 2.0 * Math.PI);
		public static final double maxRotVel = 2.0;
		public static final double maxStopDistance = 0.5 * maxVel * maxVel / linearAccel; // from max V to 0 @ linearAccel
		
		
	}
	
}
