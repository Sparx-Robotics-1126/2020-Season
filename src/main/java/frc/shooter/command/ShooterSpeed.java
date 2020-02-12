package frc.shooter.command;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.MathHelpers;

public class ShooterSpeed extends ShooterCommand {
	double speedVar = 1; //final power inputed into output; manipulated every time execute is called to reach desired rps
	// double previousRps; //set to currentRps before return to be used in the next execute
	int isReady = 0; //used to make sure motor speed is stable; runs test 8 times and resets if power is outside desired range
	// final double maxIncrease = 1.02; //used to quickly increase power to motors
	// final double minIncrease = 1.005; //used to gradually increase power to motors
	// final double maxDecrease = 0.98; //used to quickly decrease power to motors
	// final double midDecrease = 0.99; //used to decrease power to motors
	// final double minDecrease = 0.995; //used to gradually decrease power to motors
	double p = .01;

	public ShooterSpeed(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
	}
	
	public ShooterOutput execute() {
		double speed = sensors.getShooterSpeed();
		double wantedSpeed = MathHelpers.getShootingSpeed(sensors.getDistanceToTarget());
		speedVar += (speed-wantedSpeed)*p;
		if(speedVar>1){
			speedVar = 1;
		}
		return new ShooterOutput(speedVar, false); //returns edited speedVar to the power output
	}
}
