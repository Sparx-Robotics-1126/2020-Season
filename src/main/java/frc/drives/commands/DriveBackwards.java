package frc.drives.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.health.HealthReport;

public class DriveBackwards extends DrivesCommand {

	private final double DISTANCE_kP;
	private final double GYRO_kP;
	private final double DISTANCE_DEADBAND;

	private final double TARGET_DISTANCE;
	private final double TARGET_ANGLE;
	private double prevLeftDistance;
	private double currentLeftDistance;
	private double prevRightDistance;
	private double currentRightDistance;

	public DriveBackwards(DrivesSensorInterface sensors, double distance) {
		super(sensors);
		
		DISTANCE_kP = 0.03;
		GYRO_kP = 0.06;
		DISTANCE_DEADBAND = 1;//2 inches

		TARGET_DISTANCE = sensors.getAverageEncoderDistance() - distance;
		TARGET_ANGLE = sensors.getGyroAngle();

		prevLeftDistance = sensors.getLeftEncoderDistance();
		currentLeftDistance = sensors.getLeftEncoderDistance();
		prevRightDistance = sensors.getRightEncoderDistance();
		currentRightDistance = sensors.getRightEncoderDistance();
	}

	public DrivesOutput execute() {
		prevLeftDistance = currentLeftDistance;
		prevRightDistance = currentRightDistance;
				
		double distanceError = TARGET_DISTANCE - sensors.getAverageEncoderDistance();
		double angleError = TARGET_ANGLE - sensors.getGyroAngle();//Negative means too far right
		
		currentRightDistance = sensors.getRightEncoderDistance();
		currentLeftDistance = sensors.getLeftEncoderDistance();

		double leftSpeed, rightSpeed;
		leftSpeed = rightSpeed = distanceError * DISTANCE_kP;
		if(leftSpeed < -1){
			leftSpeed = -1;
			rightSpeed = -1;
		}
		double gyroOffset = angleError * GYRO_kP;

		if(gyroOffset > 0){//Too Far Left
			rightSpeed += gyroOffset;
		}else{
			leftSpeed -= gyroOffset;
		}

		if(sensors.getAverageEncoderDistance() < TARGET_DISTANCE + DISTANCE_DEADBAND){
			return new DrivesOutput(0, 0, true);
		}
		return new DrivesOutput(leftSpeed, rightSpeed);
	}
	
	@Override
	public HealthReport checkHealth() {
		if (currentRightDistance > prevRightDistance) {
			return new HealthReport(true, "Right encoder is moving in the wrong direction!");
		} else if (currentLeftDistance > prevLeftDistance) {
			return new HealthReport(true, "Left encoder is moving in the wrong direction!");
		} else if (sensors.getGyroAngle() > TARGET_ANGLE + (TARGET_ANGLE * 0.2)) {
			return new HealthReport(true, "Robot is above the expected angle range!");
		} else if (sensors.getGyroAngle() < TARGET_ANGLE - (TARGET_ANGLE * 0.2)) {
			return new HealthReport(true, "Robot is below the expected angle range!");
		}
		return new HealthReport();
	}
}