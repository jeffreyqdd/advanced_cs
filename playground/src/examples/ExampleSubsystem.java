package examples;

import botcore.framework.BotTaskManager;
import botcore.framework.Subsystem;
import botcore.framework.TickEvent;
import botcore.framework.TickType;

public class ExampleSubsystem extends Subsystem
{
	public ExampleSubsystem(String name, BotTaskManager botmgr, double rate)
	{
		super(name, botmgr, rate);
		
		sensorBinding = new ExampleSensingTask(botmgr,100000);
		actuatorBinding = new ExampleActuationTask(botmgr);
	}
	
	@Override
	public void registerTickTypeOfInterest()
	{
		getTtoi().add(TickType.CLOCK_UPDATED);
	}
	
	@Override
	public void execute(TickEvent tk)
	{
		System.out.println(sensorBinding.getReading().TIMESTAMP);
		actuatorBinding.actuate(new Object());
	}
	
}
