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
	private final double STARTING_LEFT;
	private final double STARTING_RIGHT;
	
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
		STARTING_LEFT  = currentLeftDistance;
		STARTING_RIGHT = currentRightDistance;
		
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
		if(sensors.getLeftEncoderSpeed()>0 && sensors.getRightEncoderSpeed()>0){
			return new HealthReport(true,"Both L/R not moving!");
		}

		else if(sensors.getLeftEncoderSpeed()>=0){
			return new HealthReport(true,"RIO side not moving!");
		}else if(sensors.getRightEncoderSpeed()>=0){
			return new HealthReport(true,"Scissor Lift side not moving!");
		}
		
		else if (sensors.getLeftEncoderDistance() > STARTING_LEFT-1) {
			return new HealthReport(true, "RIO Side encoder is moving in the wrong direction!");
		} else if (sensors.getRightEncoderDistance() > STARTING_RIGHT-1) {
			return new HealthReport(true, "Scissor Side encoder is moving in the wrong direction!");
		}

		return new HealthReport(false,"All good");
	}
}