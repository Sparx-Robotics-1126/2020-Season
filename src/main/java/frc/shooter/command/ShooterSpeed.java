package frc.shooter.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.MathHelpers;

public class ShooterSpeed extends ShooterCommand {
	double acceptableError = 1;

	public ShooterSpeed(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
		SmartDashboard.putNumber("Wanted speed", 0);
		SmartDashboard.putNumber("kP",Double.MIN_VALUE);
	}
	
	public ShooterOutput execute() { 
		SmartDashboard.getNumber("kP", .01);

		// double distance = sensors.getDistanceToTarget();
		// double wantedSpeed = MathHelpers.getShootingSpeed(distance); //Calc wanted speed from are distance 
		double wantedSpeed = SmartDashboard.getNumber("Wanted speed", 0);

		double actualSpeed = sensors.getShooterSpeed();
		//returns output from pid loop, and true/false if we are close enough to wanted speed
		return new ShooterOutput(wantedSpeed, (Math.abs(actualSpeed-wantedSpeed)<acceptableError)); 
	}
}
