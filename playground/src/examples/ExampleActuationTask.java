package examples;

import botcore.binding.ActuatorBindingBase;
import botcore.framework.BotTaskManager;

public class ExampleActuationTask extends ActuatorBindingBase
{
	public ExampleActuationTask(BotTaskManager botmg)
	{
		super(botmg);
	}
	
	@Override
	public void actuate(Object obj)
	{
		System.out.println("actuating");
	}
}
