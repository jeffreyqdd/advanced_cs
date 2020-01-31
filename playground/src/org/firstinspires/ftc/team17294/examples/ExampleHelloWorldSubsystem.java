package org.firstinspires.ftc.team17294.examples;

import org.firstinspires.ftc.team17294.botcore.base.CartesianVelocity;
import org.firstinspires.ftc.team17294.botcore.framework.BotTaskManager;
import org.firstinspires.ftc.team17294.botcore.framework.Subsystem;
import org.firstinspires.ftc.team17294.botcore.framework.TickEvent;
import org.firstinspires.ftc.team17294.botcore.framework.TickType;
import org.firstinspires.ftc.team17294.botcore.kinematic.mecanum.FourMecanumKinematic;

import java.util.List;

public class ExampleHelloWorldSubsystem extends Subsystem
{
	
	FourMecanumKinematic kinematic;
	
	
	public ExampleHelloWorldSubsystem(String name, BotTaskManager botmgr, double rate)
	{
		super(name, botmgr, rate);
		
		
		kinematic = new FourMecanumKinematic();
	}
	
	@Override
	public void registerTickTypeOfInterest()
	{
		getTtoi().add(TickType.CLOCK_UPDATED);
	}
	
	@Override
	public void execute(TickEvent tk)
	{
		if(tk.type == TickType.CLOCK_UPDATED)
		{
			System.out.println("hello world");
			
			List<Double> list =  kinematic.cartesianVelocityToWheelVelocities(new CartesianVelocity());
			
			
		}
	}
}
