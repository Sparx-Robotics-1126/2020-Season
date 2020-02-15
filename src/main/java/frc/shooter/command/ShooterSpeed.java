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

	double kP = .1;
	double kI = 0;
	double kD = 0;

	double output = 1;

	double acceptableError = 1;

	public ShooterSpeed(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
		SmartDashboard.putNumber("Wanted speed", 0);
		SmartDashboard.putNumber("kP",Double.MIN_VALUE);
	}
	
	public ShooterOutput execute() { 
		SmartDashboard.getNumber("kP", .01);

		double distance = sensors.getDistanceToTarget();
		// double wantedSpeed = MathHelpers.getShootingSpeed(distance); //Calc wanted speed from are distance 
		double wantedSpeed = SmartDashboard.getNumber("Wanted speed", 0);

		double actualSpeed = sensors.getShooterSpeed();
		
		double error =  wantedSpeed - actualSpeed;
		double integral = integralPrior + error;
		double derivative = (error-errorPrior);
		output += kP*error+kI*integral+kD*derivative;

		if(output > 1){
			output = 1;
		}
		if(output < 0){
			output = 0;
		}

		errorPrior = error;
		integralPrior = integral;

		// SmartDashboard.putNumber("Actual Speed", actualSpeed);
		// SmartDashboard.putNumber("Wanted Speed", wantedSpeed);

		output = wantedSpeed;
		SmartDashboard.putNumber("Output", output);
		
		//returns output from pid loop, and true/false if we are close enough to wanted speed
		return new ShooterOutput(output, (Math.abs(actualSpeed-wantedSpeed)>acceptableError)); 
	}
}
