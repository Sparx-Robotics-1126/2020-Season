package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class DriveBackwards extends DrivesCommand {

	private double startRightPosition;
	private double speedReductionRatio;
	private double leftSpeed = 0;
	private double rightSpeed = 0; 
	private double p = -0.00075;
	private double adjustmentSpeed = sensors.getGyroAngle() * p;
	private double speed;
	private double distance;
	private double startLeftPosition;
	private double startAngle;

	public DriveBackwards(DrivesSensorInterface sensors, double speed, double distance) {
		super(sensors);
		this.speed = speed; 
		this.distance = distance;
		this.speedReductionRatio = -0.975;
		this.startRightPosition = sensors.getRightEncoderDistance();
		this.startLeftPosition = sensors.getLeftEncoderDistance();
		this.startAngle = sensors.getGyroAngle();
		leftSpeed = speed;
		rightSpeed = speed;
	}
	public DrivesOutput execute() {
		double currentLeftDistance  = sensors.getLeftEncoderDistance() - this.startLeftPosition;
		double currentRightDistance  = sensors.getRightEncoderDistance() - this.startRightPosition;

		if (currentLeftDistance >= distance || currentRightDistance >= distance) {

			return new DrivesOutput(0, 0, true);	

		}
		else {
			if (sensors.getGyroAngle() > startAngle) {

				leftSpeed = leftSpeed + adjustmentSpeed; 
				rightSpeed = speed;
			}
			else if (sensors.getGyroAngle() < startAngle) {

				rightSpeed = rightSpeed + adjustmentSpeed;
				leftSpeed = speed;
			}
			else {
				rightSpeed = speed;
				leftSpeed = speed;
			}
		}

		return new DrivesOutput(leftSpeed, rightSpeed);				
	}
}