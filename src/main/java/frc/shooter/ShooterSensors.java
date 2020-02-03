package frc.shooter;

import frc.sensors.Limelight;
import frc.robot.IO;
import edu.wpi.first.wpilibj.Encoder;

public class ShooterSensors implements ShooterSensorsInterfeace 
{
	Limelight limeSensor;
	Encoder turretEncoder;
	Encoder flywheelEncoder;
	
	public ShooterSensors()
	{
		limeSensor = new Limelight();
		turretEncoder = new Encoder(IO.TURRET_ENCODER_A, IO.TURRET_ENCODER_B);
		flywheelEncoder = new Encoder(IO.FLYWHEEL_ENCODER_A, IO.FLYWHEEL_ENCODER_B);
	}
	public double getDistanceToTarget() 
	{
		return limeSensor.getDistanceFromTarget();
	}
	
	public double getAngleToTarget() 
	{
		return limeSensor.getAngleFromTarget();
	}
	
	public double getShooterAngleToRobot()
	{
		return turretEncoder.getDistance();
	}
	
	public double getShooterSpeed() 
	{
		return flywheelEncoder.getRate();
	}
	
	public boolean getTargetLock() 
	{
		return limeSensor.getLock();
	}
	
}