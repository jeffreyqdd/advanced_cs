import botcore.framework.BotTaskManager;
import examples.ExampleSubsystem;

public class Main
{
	public static void main(String[] args)
	{
		BotTaskManager bot = new BotTaskManager();
		
		//bot.getTasks().add(new examples.helloworld("hello world", bot, 1000));
		
		
		bot.getTasks().add(new ExampleSubsystem("hello world", bot, 1));
		
		bot.run();
	}
}
