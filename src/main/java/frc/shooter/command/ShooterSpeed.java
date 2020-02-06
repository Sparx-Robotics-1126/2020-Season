package frc.shooter.command;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.MathHelpers;

public class ShooterSpeed extends ShooterCommand {
	double speedVar = 1;
	double previousSpeed;
	int isReady = 0; //used to make sure motor speed is stable
	
	public ShooterSpeed(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
	}
	
	public ShooterOutput execute() {
			
		double desiredRps = MathHelpers.getShootingSpeed(sensors.getDistanceToTarget());
		double currentRps = sensors.getShooterSpeed();
		
		
		if (currentRps <= 0.95 * desiredRps) {
			previousSpeed = currentRps;
			return new ShooterOutput(1, false);
		}
		else if (currentRps <= 0.98 * desiredRps) {
			if (previousSpeed < currentRps) { //check to make sure speed is increasing
				previousSpeed = currentRps;
				speedVar = speedVar * 0.995;
				return new ShooterOutput(speedVar, false);
			}
			else {
				if (speedVar * 1.02 > 1.0) { //makes sure returned value is not larger than 1.0
					speedVar = speedVar * 1.02;
					previousSpeed = currentRps;
					return new ShooterOutput(speedVar, false);
				}
				else {
					previousSpeed = currentRps;
					return new ShooterOutput(1.0, false);
				}
			}
		}
		else if (currentRps >= 1.1 * desiredRps) {
			if (previousSpeed <= currentRps) { //changes faster if still going wrong direction
				speedVar = speedVar * 0.98;
				previousSpeed = currentRps;
				
				return new ShooterOutput(speedVar, false);
			}
			else {
				speedVar = speedVar * 0.99;
				previousSpeed = currentRps;
				return new ShooterOutput(speedVar, false);
			}
		}
		else if (currentRps >= 1.02 * desiredRps) {
			if (previousSpeed <= currentRps) {
				speedVar = speedVar * 0.99;
				previousSpeed = currentRps;
				isReady = 0;
				return new ShooterOutput(speedVar, false);
			}
			else {
				speedVar = speedVar * 0.9975;
				previousSpeed = currentRps;
				return new ShooterOutput(speedVar, false);
			}
		}
		else if (currentRps >= 0.98 * desiredRps && currentRps <= 1.02 * desiredRps) {
			if (previousSpeed > currentRps) {
				speedVar = speedVar * 1.0005;
				if (isReady == 8) {
					return new ShooterOutput(speedVar, true);
				}
				else {
					isReady += 1;
					return new ShooterOutput(speedVar, false);
				} 
			}
			else if (previousSpeed < currentRps) {
				speedVar = speedVar * 0.9995;
				if (isReady == 8) {
					return new ShooterOutput(speedVar, true);
				}
				else {
					isReady += 1;
					return new ShooterOutput(speedVar, false);
				}
			}
		}
		previousSpeed = currentRps;
		return new ShooterOutput(speedVar, false);
	}
}
