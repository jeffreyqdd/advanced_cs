package org.firstinspires.ftc.team17294.simulator.render;

import org.firstinspires.ftc.team17294.simulator.StdDraw;

public class RenderUtilities
{
	
	public static void renderMap(float sizeX, float sizeY, String fileName)
	{
		StdDraw.picture(0,0, fileName, sizeX, sizeY);
	}
	
	public static void renderRobot(int x, int y, int theta, String filename, float scaleX, float scaleY)
	{
		StdDraw.picture(x,y,filename, scaleX, scaleY, theta);
	}
}
