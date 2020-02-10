package org.firstinspires.ftc.teamcode.examples;

import org.firstinspires.ftc.teamcode.botcore.binding.packages.ControllerPackage;
import org.firstinspires.ftc.teamcode.botcore.binding.packages.MeasurementPackage;
import org.firstinspires.ftc.teamcode.botcore.framework.BotTaskManager;
import org.firstinspires.ftc.teamcode.botcore.framework.Subsystem;
import org.firstinspires.ftc.teamcode.botcore.framework.TickEvent;
import org.firstinspires.ftc.teamcode.botcore.framework.TickType;

public class ExampleCombinedSubsystm extends Subsystem
{
	
	ControllerPackage gamepad1, gamepad2;
	public ExampleCombinedSubsystm(String name, BotTaskManager botmgr, double rate)
	{
		super(name, botmgr, rate);
	
		sensorBinding = new ExampleSensingTask(botmgr, 50);
		actuatorBinding = new ExampleActuationTask(botmgr);
		
		this.gamepad1 = botmgr.controllerIO.gamepad1;
		this.gamepad2 = botmgr.controllerIO.gamepad2;
	}
	
	@Override
	public void registerTickTypeOfInterest()
	{
		getTtoi().add(TickType.CLOCK_UPDATED);
	}
	
	@Override
	public void execute(TickEvent tk)
	{
		MeasurementPackage pkg = sensorBinding.getReading();
		actuatorBinding.actuate(new Object());
		
		//System.out.println(gamepad1.a);
	}
}
