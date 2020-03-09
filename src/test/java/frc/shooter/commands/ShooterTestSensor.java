package frc.shooter.commands;

import frc.shooter.ShooterSensorsInterfeace;

public class ShooterTestSensor implements ShooterSensorsInterfeace{

	public double shooterSpeed;
	public double distanceToTarget; 
	public double angleToTarget;
	public double shooterAngle;

	@Override
	public double getDistanceToTarget() {
		return distanceToTarget;
	}

	@Override
	public double getAngleToTarget() {
		// TODO Auto-generated method stub
		return this.angleToTarget;
	}

	@Override
	public double getShooterAngleToRobot() {
		// TODO Auto-generated method stub
		return this.shooterAngle;
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

	@Override
	public void enableLimelight(boolean enable) {}
	
}
