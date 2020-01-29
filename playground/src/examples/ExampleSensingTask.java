package examples;

import botcore.binding.MeasurementPackage;
import botcore.binding.SensorBindingBase;
import botcore.framework.BotTaskManager;

public class ExampleSensingTask extends SensorBindingBase
{
	int sensor1;
	
	public ExampleSensingTask(BotTaskManager botmgr, long delay)
	{
		super(botmgr, delay);
	}
	
	
	@Override
	public void createSensors()
	{
		sensor1 = -1;
	}
	
	@Override
	public MeasurementPackage sense()
	{
		return new MeasurementPackage();
	}
	
}
