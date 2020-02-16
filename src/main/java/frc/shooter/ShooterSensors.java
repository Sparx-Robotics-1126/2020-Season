package frc.shooter;

import frc.sensors.Limelight;
import frc.robot.IO;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;

public class ShooterSensors implements ShooterSensorsInterfeace 
{
	private Limelight limeSensor;
	private Encoder turretEncoder;
	private TalonSRX flywheelEncoder;


	public ShooterSensors(TalonSRX shooterFlywheel){
		limeSensor = new Limelight();
		turretEncoder = new Encoder(IO.TURRET_ENCODER_A, IO.TURRET_ENCODER_B);
		turretEncoder.setDistancePerPulse(0.314789);
		this.flywheelEncoder = shooterFlywheel;

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
		return flywheelEncoder.getSelectedSensorVelocity()*10/(1024.0);
	}

	public boolean getTargetLock() 
	{
		return limeSensor.getLock();
	}

}