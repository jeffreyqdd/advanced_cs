package org.firstinspires.ftc.team17294.examples;

import org.firstinspires.ftc.team17294.botcore.framework.BotTaskManager;
import org.firstinspires.ftc.team17294.botcore.framework.Subsystem;
import org.firstinspires.ftc.team17294.botcore.framework.TickEvent;
import org.firstinspires.ftc.team17294.botcore.framework.TickType;
import org.firstinspires.ftc.team17294.botcore.important.ControllerIO;

public class ExampleSubsystem extends Subsystem
{
	ControllerIO controllerIO;
	
	public ExampleSubsystem(String name, BotTaskManager botmgr, double rate)
	{
		super(name, botmgr, rate);
		
		sensorBinding = new ExampleSensingTask(botmgr,100);
		actuatorBinding = new ExampleActuationTask(botmgr);
		
		controllerIO = botmgr.controllerIO;
	}
	
	@Override
	public void registerTickTypeOfInterest()
	{
		getTtoi().add(TickType.CLOCK_UPDATED);
	}
	
	@Override
	public void execute(TickEvent tk)
	{
		System.out.println(controllerIO.gamepad1.a);
		
		System.out.println("tick!");
	}
	
}
