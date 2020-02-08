package frc.shooter.command;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.MathHelpers;

public class ShooterSpeed extends ShooterCommand {
	double speedVar = 1; //final power inputed into output; manipulated every time execute is called to reach desired rps
	double previousRps; //set to currentRps before return to be used in the next execute
	int isReady = 0; //used to make sure motor speed is stable; runs test 8 times and resets if power is outside desired range
	final double maxIncrease = 1.02; //used to quickly increase power to motors
	final double minIncrease = 1.005; //used to gradually increase power to motors
	final double maxDecrease = 0.98; //used to quickly decrease power to motors
	final double midDecrease = 0.99; //used to decrease power to motors
	final double minDecrease = 0.995; //used to gradually decrease power to motors
	public ShooterSpeed(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
	}
	
	public ShooterOutput execute() {
			
		double desiredRps = MathHelpers.getShootingSpeed(sensors.getDistanceToTarget());
		double currentRps = sensors.getShooterSpeed();
		
		
		if (currentRps <= 0.95 * desiredRps) { //if speed is less than 95% of desired speed, give motors full power
			previousRps = currentRps;
			return new ShooterOutput(1, false); //first number is power from 0 - 1, false is boolean for whether or not it is ready to shoot
		}
		else if (currentRps <= 0.98 * desiredRps) { //rps between 95% and 98% of target value
			if (previousRps < currentRps) { //check to make sure speed is increasing
				speedVar = speedVar * minDecrease; //change speedVar and skip to bottom; applies to every case without return statement
				isReady = 0; //resets number of tries when rps leaves target range
			}
			else {
				if (speedVar * 1.02 > 1.0) { //makes sure returned value is not larger than 1.0
					isReady = 0;
					previousRps = currentRps;
					return new ShooterOutput(1.0, false); 
				}
				else {
					speedVar = speedVar * maxIncrease;
					isReady = 0;
				}
			}
		}
		else if (currentRps >= 1.1 * desiredRps) { //if it overshoots significantly
			if (previousRps <= currentRps) { //changes faster if still accelerating
				speedVar = speedVar * maxDecrease;
			}
			else {
				speedVar = speedVar * midDecrease;
			}
		}
		else if (currentRps >= 1.02 * desiredRps) { //if it overshoots speed
			if (previousRps <= currentRps) { //changes faster if still accelerating
				speedVar = speedVar * midDecrease;
				isReady = 0;
			}
			else {
				speedVar = speedVar * minDecrease;
				isReady = 0;
			}
		}
		else if ((currentRps >= 0.98 * desiredRps) && (currentRps <= 1.02 * desiredRps)) { //if in correct range
			if (previousRps > currentRps) { //if decelerating, slightly increase speed
				speedVar = speedVar * minIncrease;
				if (isReady == 8) { //runs through 8 times to ensure it stays in the correct range
					return new ShooterOutput(speedVar, true); //returns true for ready to shoot
				}
				else {
					isReady += 1;
				} 
			}
			else if (previousRps < currentRps) { //if accelerating, slightly decrease speed
				speedVar = speedVar * minDecrease;
				if (isReady == 8) { //runs through 8 times to ensure it stays in the correct range
					return new ShooterOutput(speedVar, true); //returns true for ready to shoot
				}
				else {
					isReady += 1;
				}
			}
		}
		previousRps = currentRps; 
		return new ShooterOutput(speedVar, false); //returns edited speedVar to the power output
	}
}
