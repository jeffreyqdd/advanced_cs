package org.firstinspires.ftc.team17294.botcore.framework;

import org.firstinspires.ftc.team17294.botcore.important.ControllerIO;
import org.firstinspires.ftc.team17294.botcore.important.BotConfiguration;
import org.firstinspires.ftc.team17294.botcore.utilities.LogUtils;

import java.util.HashSet;
import java.util.logging.Logger;

public class BotTaskManager
{
	
	/* This block only needs to be invoked once for the entire project*/
	static {
		LogUtils.init();
	}
	// this logger needs to be created per class
	Logger logger = LogUtils.getLogger(BotTaskManager.class.getName());
	
	//controller data
	public ControllerIO controllerIO;
	
	//create bot configuration as well as the tasks
	protected final BotConfiguration config;
	protected final HashSet<Subsystem> tasks;
	protected final WallClock clock;
	
	public BotTaskManager(boolean createGamepad) {
		super();
		
		this.config = new BotConfiguration();
		this.clock = new WallClock();
		
		tasks = new HashSet<>();
		
		
		if(createGamepad)
			getTasks().add(new ControllerIO("controller IO", this, 30));
	}
	
	public HashSet<Subsystem> getTasks() {
		return tasks;
	}
	
	
	// logic in one loop
	public void loop(long currentTimeMillis) {
		
		// create clock_update event
		TickEvent clockEvent = new TickEvent(TickType.CLOCK_UPDATED, currentTimeMillis, null);
		
		// run every task once, the CLOCK_UPDATED event contains clock info
		for (Subsystem task : tasks) {
			
			long taskStartTime = clock.currentTimeMillis();
			
			task.tick(clockEvent); // forward
			
			long elapsed = clock.currentTimeMillis() - taskStartTime;
			
			// there is nested events... thus a longer time
			if (elapsed > 10) {
				logger.fine("LONGER_TIME "+task.getName()+" " + elapsed);
			}
			
		}
		
		
	}
	
	// this method serve as a simple built in loop runner
	public void run() {
		
		long prevTickTime = clock.currentTimeMillis();
		
		while (true) {
			long currentTickTime = clock.currentTimeMillis();
			long passedTickTime = currentTickTime - prevTickTime;
			prevTickTime = currentTickTime;
			
			if (passedTickTime == 0)
				continue;
			
			loop(currentTickTime);
			
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// this method will be invoked by Tasks
	// it will look into the registered task interest and send their mailbox the event
	// this tick is not clock-update. but more of other tick types
	public void multicastTick(TickEvent t) {
		if (t.ts <= 0) {
			t.ts = clock.currentTimeMillis();
		}
		
		if ( t.type == TickType.CLOCK_UPDATED ) {
			//logger.finer("clock update event: " + t);
		}
		else if (t.type.ordinal() > 8){
			logger.info("LOW_FREQ events. (turned on FINE to see high freq events)" + t);
		}
		else {
			logger.fine("HIGH_FREQ events:" + t);
		}
		
		int cnt = 0;
		for (Subsystem task : tasks) {
			
			if (task.isTickTypeOfInterest(t.type)) {
				
				task.sendEvent(t); // forward to the task mailbox
				
				cnt ++;
			}
		}
		
		if (cnt == 0) {
			if (!t.isNoReceiverOk) {
				logger.warning("NO receiver for event: " + t);
			}
		}
	}
	
	public BotConfiguration getConfig() {
		return config;
	}
	
	
	public WallClock getClock() {
		return clock;
	}
}
