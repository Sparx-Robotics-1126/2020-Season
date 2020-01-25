package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class DriveForward extends DrivesCommand {

	private double speed;
	private double distance;
	private double centerAngle;
	private boolean startMoving;
	private double startLeftDistance;
	private double startRightDistance;
	
		public DriveForward(DrivesSensorInterface sensors, double speed, double distance) {
			super(sensors);
			this.speed = speed;
			this.distance = distance;
			this.startMoving = false;
		}
		public DrivesOutput execute() {
			if(this.startMoving == false) {
				this.startRightDistance = sensors.getRightEncoderDistance();
				this.startLeftDistance = sensors.getLeftEncoderDistance();
				this.centerAngle = sensors.getGyroAngle();
				this.startMoving = true;
			}
			
			return new DrivesOutput(speed, speed);
			
	}
}