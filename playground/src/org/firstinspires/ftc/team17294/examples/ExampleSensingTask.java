package org.firstinspires.ftc.team17294.examples;

import org.firstinspires.ftc.team17294.botcore.binding.SensorBindingBase;
import org.firstinspires.ftc.team17294.botcore.binding.packages.MeasurementPackage;
import org.firstinspires.ftc.team17294.botcore.framework.BotTaskManager;

public class ExampleSensingTask extends SensorBindingBase
{
	
	private int sensor1;
	
	public ExampleSensingTask(BotTaskManager botmgr, long delay)
	{
		super(botmgr, delay);
	}
	
	@Override
	public void createSensors()
	{
		sensor1 = 1;
	}
	
	@Override
	public MeasurementPackage sense()
	{
		return new MeasurementPackage();
	}
}
