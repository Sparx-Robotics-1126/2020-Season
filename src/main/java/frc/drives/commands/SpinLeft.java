package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class SpinLeft extends DrivesCommand {
	
	private final double SPEED;
	private final double ANGLE;
	
	public SpinLeft(DrivesSensorInterface sensors, double speed, double angle) {
		super(sensors); //Superclass will store sensor for you!
		this.SPEED = speed;
		this.ANGLE = angle;
	}

	/**
	 * Main Method, will be called constantly until isDone is set true
	 */
	@Override
	public DrivesOutput execute() {
		if(sensors.getGyroAngle() >= ANGLE) { //IF we have spun more then or equal to our wanted angle
			//Stop motors, let drives know we are finished
			return new DrivesOutput(0, 0, true); //LeftMotor: 0, RightMotor: 0, isDone: true
		}else {
			//If we still need to spin
			return new DrivesOutput(-SPEED, SPEED);//LeftMotor: -SPEED, RightMotor: SPEED, isDone: false
			//Can also use new DrivesOutput(SPEED, -SPEED, false)
		}
	}
}
