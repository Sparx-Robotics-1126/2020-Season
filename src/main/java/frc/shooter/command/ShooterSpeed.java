package frc.shooter.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.MathHelpers;

public class ShooterSpeed extends ShooterCommand {
	double errorPrior;
	double integralPrior;

	double kP = 1;
	double kI = 0;
	double kD = 0;

	double acceptableError = 1;

	public ShooterSpeed(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
	}
	
	public ShooterOutput execute() { 
		double distance = sensors.getDistanceToTarget();
		double wantedSpeed = MathHelpers.getShootingSpeed(distance); //Calc wanted speed from are distance 
		double actualSpeed = sensors.getShooterSpeed();
		
		double error =  wantedSpeed - actualSpeed;
		double integral = integralPrior + error;
		double derivative = (error-errorPrior);
		double output = kP*error+kI*integral+kD*derivative;

		errorPrior = error;
		integralPrior = integral;

		SmartDashboard.putNumber("Actual Speed", actualSpeed);
		SmartDashboard.putNumber("Wanted Speed", wantedSpeed);

		//returns output from pid loop, and true/false if we are close enough to wanted speed
		return new ShooterOutput(output, (Math.abs(actualSpeed-wantedSpeed)>acceptableError)); 
	}
}
