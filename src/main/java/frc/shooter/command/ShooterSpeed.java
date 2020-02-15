package frc.shooter.command;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;
import frc.shooter.MathHelpers;

public class ShooterSpeed extends ShooterCommand {
	private double currentRps;
	private double desiredRps;
	private double outputValue = 0.5;
	private boolean readyToShoot = false;
	private int isReady = 0; //used to make sure motor speed is stable; runs test 8 times and resets if power is outside desired range
	private final double FACTOR = 0.2; //change this to increase speed that the power responds; starts at 20%
	private final double MIN_POWER_OUTPUT = 0.1; //used to make sure function does not output too low of a power value and take a long time to increase.
	private final double TARGET_RANGE_OFFSET = 0.02; //allows for the motor to be this amount off while still returning true
	private final int CONFIRM_STABILITY_COUNT = 5; //returns false this many times while in target range to ensure speed is stable
	public ShooterSpeed(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
	}
	
	public ShooterOutput execute() { //will be called every 5 milliseconds; requires speed from 0 to 1 and a boolean for if it is ready to shoot
		
		// Read from LimeLight, convert to desired RPS
		this.desiredRps = MathHelpers.getShootingSpeed(sensors.getDistanceToTarget());
		this.currentRps = sensors.getShooterSpeed();
		double speedRatio; 
		this.readyToShoot = false;
		
		if (this.desiredRps == 0) { //returns output of 0 when desired is 0 to prevent divide by 0 errors
			this.outputValue = 0;
			return new ShooterOutput(this.outputValue, false); //false means not ready to shoot
		}
		//changes motor speed faster while further away from desired and slower while closer
		if (isBelowTarget()) { //if the robot should accelerate 
			//calculate difference between desired and current over desired; output is larger based on how far apart the numbers are
			speedRatio = (this.desiredRps - this.currentRps)/this.desiredRps;
			this.outputValue += this.outputValue * (FACTOR * speedRatio); //changes speed by calculated value times change factor
		}
		else {
			speedRatio = (this.currentRps - this.desiredRps)/this.currentRps; //does similar function as above code, but decelerates
			this.outputValue -= this.outputValue * (FACTOR * speedRatio);
		}
		
		if ((this.currentRps >= (1 - TARGET_RANGE_OFFSET) * this.desiredRps)
				&& (this.currentRps <= (1 + TARGET_RANGE_OFFSET) * this.desiredRps)) { //if within desired range
			if (this.isReady > CONFIRM_STABILITY_COUNT) { //checks to make sure speed is stable
				this.readyToShoot = true;
			}
			else {
				this.isReady += 1; //runs specified number of times before returning true
			}
		}
		else {
			this.isReady = 0; //resets outside target range
		}
		
		if (this.outputValue > 1) {
			this.outputValue = 1; //ensures outputted value is not greater than 1
		}
		if (this.outputValue < MIN_POWER_OUTPUT) {
			this.outputValue = MIN_POWER_OUTPUT; //ensures outputted value is not less than 0
		}
		
		return new ShooterOutput(this.outputValue, readyToShoot);
	}
	
	private boolean isBelowTarget() {
		return (desiredRps > currentRps);
	}
	
	
	public boolean isReadyToShoot(double currentRps, double desiredRps) { //Want to be within 2% of target RPS
		if ((currentRps >= 0.98 * desiredRps) && (currentRps <= 1.02 * desiredRps)) {
			return true;
		}
		return false;
	}
}
