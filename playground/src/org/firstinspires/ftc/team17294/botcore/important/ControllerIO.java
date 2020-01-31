package org.firstinspires.ftc.team17294.botcore.important;

import org.firstinspires.ftc.team17294.botcore.binding.packages.ControllerPackage;
import org.firstinspires.ftc.team17294.botcore.framework.BotTaskManager;
import org.firstinspires.ftc.team17294.botcore.framework.Subsystem;
import org.firstinspires.ftc.team17294.botcore.framework.TickEvent;
import org.firstinspires.ftc.team17294.botcore.framework.TickType;

public class ControllerIO extends Subsystem
{
	//==============================================
	//do not touch this
	//==============================================
	
	public ControllerPackage gamepad1, gamepad2;
	
	public ControllerIO(String name, BotTaskManager botmgr, double rate)
	{
		super(name, botmgr, rate);
		
		gamepad1 = new ControllerPackage();
		gamepad2 = new ControllerPackage();
		
		botmgr.controllerIO = this;
	}
	
	@Override
	public void registerTickTypeOfInterest()
	{
		getTtoi().add(TickType.CLOCK_UPDATED);
	}
	
	
	//==============================================
	//only touch this
	//==============================================
	@Override
	public void execute(TickEvent tk)
	{
		gamepad1.a = 0.3;
	}
}
