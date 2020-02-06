package org.firstinspires.ftc.team17294.botcore.kinematic.algorithms;

import org.firstinspires.ftc.team17294.botcore.important.BotConfiguration;

import java.util.LinkedList;
import java.util.List;

public class MecanumUtillities implements KinematicUtilitiesBase
{
	
	@Override
	public List<Double> wheelVelocitiesToMotorPowerWithEncoders(List<Double> linearSpeeds)
	{
		List<Double> wheelPowers = new LinkedList<>();
		
		for(Double d : linearSpeeds)
		{
			double newPower = d / BotConfiguration.Kinematic.maxVel;
			if(newPower > 1.0) newPower = 1;
			else if(newPower < -1.0) newPower = -1;
			wheelPowers.add(newPower);
		}
		
		
		return wheelPowers;
	}
}
