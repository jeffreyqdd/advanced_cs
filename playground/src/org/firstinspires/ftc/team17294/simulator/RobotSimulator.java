package org.firstinspires.ftc.team17294.simulator;

import org.firstinspires.ftc.team17294.simulator.render.RenderUtilities;

import java.util.ArrayList;
import java.util.List;

public class RobotSimulator
{
	//constants
	private static float cmPerInch = 2.54f;
	
	
	//robot location
	private double GLOBAL_X;
	private double GLOBAL_Y;
	private double GLOBAL_THETA;
	
	
	//canvas stuff
	private final float SCALE_X = cmPerInch * 144;
	private final float SCALE_Y = cmPerInch * 144;
	
	//data for map image
	private static final String mapFile = "src/org/firstinspires/ftc/team17294/simulator/data/skystone2019.png";
	
	
	//data for robot image
	private static final String robotFile = "src/org/firstinspires/ftc/team17294/simulator/data/robot.png";
	private static float robotX = cmPerInch * 18;
	private static float robotY = cmPerInch * 18;
	
	
	public RobotSimulator()
	{
		GLOBAL_X = 0;
		GLOBAL_Y = 0;
		GLOBAL_THETA = 0;
		
		
		StdDraw.setXscale(-SCALE_X/2f, SCALE_X/2f);
		StdDraw.setYscale(-SCALE_Y/2f, SCALE_Y/2f);
		
		StdDraw.enableDoubleBuffering();
	}
	
	
	public void updateRobotLocation(double x, double y, double theta)
	{
		GLOBAL_X = x;
		GLOBAL_Y = y;
		GLOBAL_THETA = theta;
	}
	
	public void updateSimulator()
	{
		StdDraw.clear();
		
		RenderUtilities.renderMap(SCALE_X, SCALE_Y, mapFile);
		RenderUtilities.renderRobot( (int) GLOBAL_X, (int) GLOBAL_Y, (int) GLOBAL_THETA, robotFile, robotX, robotY);
		
		
		StdDraw.show();
	}
	
	public List<Double> getData()
	{
		List<Double> ret = new ArrayList<>();
		
		ret.add(GLOBAL_X);
		ret.add(GLOBAL_Y);
		ret.add(GLOBAL_THETA);
		
		return ret;
	}
	
	
	
}
