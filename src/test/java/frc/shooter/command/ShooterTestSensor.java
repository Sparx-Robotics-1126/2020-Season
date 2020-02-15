package frc.shooter.command;

import frc.shooter.ShooterSensorsInterfeace;

public class ShooterTestSensor implements ShooterSensorsInterfeace{
	
	public double shooterSpeed;
	public double distanceFromTarget;
	
	@Override
	public double getDistanceToTarget() {
		// TODO Auto-generated method stub
		return this.distanceFromTarget;
	}

	@Override
	public double getAngleToTarget() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShooterAngleToRobot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShooterSpeed() {
		// TODO Auto-generated method stub
		return this.shooterSpeed;
	}

	@Override
	public boolean getTargetLock() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
