package org.firstinspires.ftc.teamcode.botcore.binding;

import org.firstinspires.ftc.teamcode.botcore.important.BotConfiguration;
import org.firstinspires.ftc.teamcode.botcore.framework.BotTaskManager;
import org.firstinspires.ftc.teamcode.botcore.utilities.LogUtils;

import java.util.logging.Logger;

public abstract class ActuatorBindingBase implements Actuator
{
	Logger logger = LogUtils.getLogger(SensorBindingBase.class.getName());
	
	protected BotTaskManager botmgr;
	protected BotConfiguration config;
	
	public ActuatorBindingBase(BotTaskManager botmgr)
	{
		super();

		this.botmgr = botmgr;
		this.config = botmgr.getConfig();
		
		createActuators();
	}
	
	public abstract void createActuators();
	
	@Override
	public abstract void actuate(Object obj);
}
