package botcore.binding;

import botcore.configuration.BotConfiguration;
import botcore.framework.BotTaskManager;
import botcore.utilities.LogUtils;

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
	}
	
	
	@Override
	public abstract void actuate(Object obj);
}
