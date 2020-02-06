package org.firstinspires.ftc.team17294.simulator.tasks;

import org.firstinspires.ftc.team17294.botcore.framework.BotTaskManager;
import org.firstinspires.ftc.team17294.botcore.framework.Subsystem;
import org.firstinspires.ftc.team17294.botcore.framework.TickEvent;
import org.firstinspires.ftc.team17294.botcore.framework.TickType;
import org.firstinspires.ftc.team17294.simulator.RobotSimulator;

import java.util.List;


public class Movement extends Subsystem
{
	
	private double currX;
	private double currY;
	private double currTheta;
	
	
	private double finalX = 100;
	private double finalY = 100;
	private double finalTheta = 0;
	
	private long lastTick = -1;
	
	private RobotSimulator sim;
	
	public Movement(String name, BotTaskManager botmgr, double rate)
	{
		super(name, botmgr, rate);
		
		sim = new RobotSimulator();
	}
	
	@Override
	public void registerTickTypeOfInterest()
	{
		getTtoi().add(TickType.CLOCK_UPDATED);
	}
	
	@Override
	public void execute(TickEvent tk)
	{
		sim.updateSimulator();
		
		List<Double> data = sim.getData();
		
		double fractionOfASecond;
		if(lastTick == -1)
		{
			long currentTick = System.currentTimeMillis();
			fractionOfASecond = (double) (currentTick - lastTick) / 1000d;
			lastTick = currentTick;
			return;
		}
		else
		{
			long currentTick = System.currentTimeMillis();
			fractionOfASecond = (double) (currentTick - lastTick) / 1000d;
			lastTick = currentTick;
		}

		
		currX = data.get(0);
		currY = data.get(1);
		currTheta = data.get(2);
		
		double errorX = ((currX - finalX) * -2.0);
		double errorY = ((currY - finalY) * -2.0);
		double errorT = ((currTheta - finalTheta) * -0.1);
		
		//fractionOfASecond = 1;
		double newX = currX + (errorX * fractionOfASecond);
		double newY = currY + (errorY * fractionOfASecond);
		

		
		System.out.println();
		
		
		sim.updateRobotLocation(newX, newY, currTheta + errorT);
		
		System.out.println(currX + " " + currY + " " + currTheta + ":   " + fractionOfASecond);
		
		
	}
}
