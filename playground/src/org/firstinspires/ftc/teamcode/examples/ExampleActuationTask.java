package org.firstinspires.ftc.teamcode.examples;

import org.firstinspires.ftc.teamcode.botcore.binding.ActuatorBindingBase;
import org.firstinspires.ftc.teamcode.botcore.framework.BotTaskManager;

public class ExampleActuationTask extends ActuatorBindingBase
{
	private int Motor1;
	
	
	public ExampleActuationTask(BotTaskManager botmgr)
	{
		super(botmgr);
	}
	
	@Override
	public void createActuators()
	{
		Motor1 = 1;
	}
	
	@Override
	public void actuate(Object obj)
	{
		Motor1 = 2;
	}
}
