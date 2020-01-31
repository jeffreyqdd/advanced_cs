package org.firstinspires.ftc.team17294.botcore.kinematic.mecanum;


import org.firstinspires.ftc.team17294.botcore.base.CartesianPosition;
import org.firstinspires.ftc.team17294.botcore.base.CartesianVelocity;
import org.firstinspires.ftc.team17294.botcore.important.BotConfiguration;

import java.util.LinkedList;
import java.util.List;

public class FourMecanumKinematic implements WheeledBaseKinematic
{
	
	@Override
	public List<Double> cartesianVelocityToWheelVelocities(CartesianVelocity chassisVelocity)
	{
		List<Double> wheelVelocities = new LinkedList<Double>();
		
		double RadPerSec_FromX, RadPerSec_FromY, RadPerSec_FromTheta;
		
		if (BotConfiguration.Kinematic.wheelRadius == 0 || BotConfiguration.Kinematic.rotationRatio == 0 || BotConfiguration.Kinematic.slideRatio == 0) {
			throw new IllegalArgumentException("The wheelRadius, RotationRatio or the SlideRatio are not allowed to be zero");
		}
		
		
		// RadPerSec_FromX = vx / config.wheelRadius;
		RadPerSec_FromX = chassisVelocity.vx / BotConfiguration.Kinematic.wheelRadius;
		RadPerSec_FromY = chassisVelocity.vy / (BotConfiguration.Kinematic.wheelRadius * BotConfiguration.Kinematic.slideRatio) ;
		
		// Calculate Rotation Component
		RadPerSec_FromTheta = ((BotConfiguration.Kinematic.lengthBetweenFrontAndRearWheels + BotConfiguration.Kinematic.lengthBetweenFrontWheels) / (2.0 * BotConfiguration.Kinematic.wheelRadius)) * chassisVelocity.omega;;
		
		wheelVelocities.add( RadPerSec_FromX + RadPerSec_FromY + RadPerSec_FromTheta );
		wheelVelocities.add( RadPerSec_FromX - RadPerSec_FromY - RadPerSec_FromTheta );
		wheelVelocities.add( RadPerSec_FromX + RadPerSec_FromY - RadPerSec_FromTheta );
		wheelVelocities.add( RadPerSec_FromX - RadPerSec_FromY + RadPerSec_FromTheta );
		
		
		return wheelVelocities;
	}
	
	@Override
	public CartesianVelocity wheelVelocitiesToCartesianVelocity(List<Double> wheelVelocities)
	{
		return null;
	}
	
	@Override
	public CartesianPosition wheelPositionsToCartesianPosition(List<Double> wheelPositions)
	{
		return null;
	}
	
	@Override
	public List<Double> cartesianPositionToWheelPositions(CartesianPosition botPosition)
	{
		return null;
	}
}