package botcore.binding;

import botcore.configuration.BotConfiguration;
import botcore.framework.BotTaskManager;
import botcore.utilities.LogUtils;

import java.util.logging.Logger;

public abstract class SensorBindingBase implements Sensor
{
	Logger logger = LogUtils.getLogger(SensorBindingBase.class.getName());
	
	private MeasurementPackage measurement;
	private long delay;
	protected BotTaskManager botmgr;
	protected BotConfiguration config;
	
	public SensorBindingBase(BotTaskManager botmgr, long delay)
	{
		super();
		this.measurement = null;
		this.botmgr = botmgr;
		this.config = botmgr.getConfig();
		this.delay = delay;
		
		
		Thread sensingThread = new SensingThread();
		sensingThread.start();
		
	}
	
	@Override
	public MeasurementPackage getReading() {
		return measurement;
	}
	
	
	public abstract void createSensors();
	public abstract MeasurementPackage sense();
	
	public class SensingThread extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				while(!isInterrupted())
				{
					measurement = sense();
					Thread.sleep(delay);
				}
			}
			catch (InterruptedException e) {logger.fine(this.getName() + " get interrupted");}
			catch (Exception e) {e.printStackTrace();}
		}
	}
	
	
}
