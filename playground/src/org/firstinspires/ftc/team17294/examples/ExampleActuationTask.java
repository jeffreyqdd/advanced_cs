package org.firstinspires.ftc.team17294.examples;

import org.firstinspires.ftc.team17294.botcore.binding.ActuatorBindingBase;
import org.firstinspires.ftc.team17294.botcore.framework.BotTaskManager;

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
