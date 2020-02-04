package org.firstinspires.ftc.team17294;

import org.firstinspires.ftc.team17294.botcore.framework.BotTaskManager;
import org.firstinspires.ftc.team17294.examples.ExampleCombinedSubsystm;

public class Main
{
	public static void main(String[] args)
	{
		//BotTaskManager bot = new BotTaskManager();
		
		
		//bot.getTasks().add(new org.firstinspires.ftc.team17294.examples.helloworld("hello world", bot, 1000));
		
		//bot.getTasks().add(new ExampleSubsystem("hello world", bot, 1));
		
		//bot.run();
		
		BotTaskManager bot = new BotTaskManager(true);
		//bot.getTasks().add(new ControllerIO("controller IO", bot, 4));
		for(int i = 0; i < 1; i++)
			bot.getTasks().add(new ExampleCombinedSubsystm("examplee hello world", bot, 60));
		
		
		bot.run();
		
		
		//System.out.println(BotConfiguration.Kinematic.maxVel);
		
	}
}
