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

	private double leftSpeed = 0;
	private double rightSpeed = 0; 
	private double p = 0.00075;
	private double ajustmentSpeed = sensors.getGyroAngle() * p;

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
			this.speedReductionRatio = 0.975;
			this.startRightPosition = sensors.getRightEncoderDistance();
			this.startLeftPosition = sensors.getLeftEncoderDistance();
			this.startAngle = sensors.getGyroAngle();
			leftSpeed = speed;
			rightSpeed = speed;
			
			
		}
		/**
		 * 
		 */
		public DrivesOutput execute() {
			double currentLeftDistance  = sensors.getLeftEncoderDistance() - this.startLeftPosition;
			double currentRightDistance  = sensors.getRightEncoderDistance() - this.startRightPosition;
			
			//is the desired distance reached 
			if (currentLeftDistance >= distance || currentRightDistance >= distance) {
				
				return new DrivesOutput(0, 0, true);	
				
			}
			else {
				if (sensors.getGyroAngle() > startAngle) {
						
						leftSpeed = leftSpeed - ajustmentSpeed; 
						System.out.println("adjusting right");
						rightSpeed = speed;
					}
					else if (sensors.getGyroAngle() < startAngle) {
						
						rightSpeed = rightSpeed - ajustmentSpeed;
						System.out.println("adjusting left");
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