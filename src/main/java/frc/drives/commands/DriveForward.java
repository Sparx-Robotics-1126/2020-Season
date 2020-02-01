package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class DriveForward extends DrivesCommand {
	//stored speed for wheels
	private double speed;
	//how far the robo should travel before stopping
	private double distance;
	// angle of robo's gyro before moving
	private double startAngle;
	//the starting value of the left encoder distance
	private double startLeftPosition;
	//the starting value of the right encoder distance
	private double startRightPosition;
	// current distance of Mr Robo
	private double speedReductionRatio;

		/**
		 * 
		 * @param sensors
		 * @param speed
		 * @param distance
		 */
		public DriveForward(DrivesSensorInterface sensors, double speed, double distance) {
			super(sensors);
			this.speed = speed; 
			this.distance = distance;
			this.speedReductionRatio = 0.9;
			this.startRightPosition = sensors.getRightEncoderDistance();
			this.startLeftPosition = sensors.getLeftEncoderDistance();
			this.startAngle = sensors.getGyroAngle();
			
		}
		/**
		 * 
		 */
		public DrivesOutput execute() {
			
			double leftSpeed = speed;
			double rightSpeed = speed; 
			double currentLeftDistance  = sensors.getLeftEncoderDistance() - this.startLeftPosition;
			double currentRightDistance  = sensors.getRightEncoderDistance() - this.startRightPosition;
			
			//is the desired distance reached 
			if (currentLeftDistance >= distance || currentRightDistance >= distance) {
				
				return new DrivesOutput(0, 0, true);	
				
			}
			else {
				if (sensors.getGyroAngle() > startAngle) {
						
						leftSpeed = leftSpeed * speedReductionRatio; 
					}
					else if (sensors.getGyroAngle() < startAngle) {
						
						rightSpeed = rightSpeed * speedReductionRatio;
					}
				}
			
				return new DrivesOutput(leftSpeed, rightSpeed);
			}
	}