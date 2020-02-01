package frc.shooter;

import frc.sensors.Limelight;
import frc.shooter.MathHelpers;
import frc.drives.DrivesSensors;

public class ShooterSensors implements ShooterSensorsInterfeace 
{
	Limelight lightSensor; //Used for accessing member methods of the limelight.
	DrivesSensors driveSensor; //Used for accessing member methods of the drive sensors.
	double cameraToRobotAngle;
	public ShooterSensors(double cameraToRobotAngle, Limelight light, DrivesSensors drive)
	{
		this.lightSensor = light;
		this.driveSensor = drive;
		this.cameraToRobotAngle = cameraToRobotAngle;
	}
	public double getDistanceToTarget() 
	{
		return this.lightSensor.getDistanceFromTarget();
	}
	
	public double getAngleToTarget() 
	{
		return this.lightSensor.getAngleFromTarget();
	}
	
	public double getShooterAngleToRobot()
	{
		return MathHelpers.getShootOffset(this.cameraToRobotAngle, getShooterSpeed(), this.driveSensor.getAverageEncoderSpeed());
	}
	
	public double getShooterSpeed() 
	{
		return MathHelpers.getShootingSpeed(getDistanceToTarget());
	}
	
	public boolean getTargetLock() 
	{
		return this.lightSensor.getLock();
	}
	
}