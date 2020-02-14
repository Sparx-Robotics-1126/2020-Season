package frc.drives;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.IO;

public class DrivesSensors implements DrivesSensorInterface {

	private AHRS gyro;
	private Encoder rightEncoder;
	private Encoder leftEncoder;
	private double rightJoystick;
	private double leftJoystick;
	
	public DrivesSensors() {
		//gyro = new AHRS(SerialPort.Port.kUSB);
		rightEncoder = new Encoder(IO.RIGHT_ENCODER_A, IO.RIGHT_ENCODER_B, true);
		rightEncoder.setDistancePerPulse(0.02110013);
		leftEncoder = new Encoder(IO.LEFT_ENCODER_A, IO.LEFT_ENCODER_B);
		leftEncoder.setDistancePerPulse(0.02110013);
		rightJoystick = 0;
		leftJoystick = 0;
	}
	
	@Override
	public double getLeftEncoderDistance() {
		return leftEncoder.getDistance();
	}

	@Override
	public double getLeftEncoderSpeed() {
		return leftEncoder.getRate();
	}

	@Override
	public double getRightEncoderDistance() {
		return rightEncoder.getDistance();
	}

	@Override
	public double getRightEncoderSpeed() {
		return rightEncoder.getRate();
	}

	@Override
	public double getAverageEncoderDistance() {
		return (getRightEncoderDistance() + getLeftEncoderDistance())/2;
	}

	@Override
	public double getAverageEncoderSpeed() {
		return (getRightEncoderSpeed() + getLeftEncoderSpeed())/2;
	}

	@Override
	public double getGyroAngle() {
		return gyro.getAngle();
	}

	@Override
	public double getRightJoyStick() {
		return rightJoystick;
	}

	@Override
	public double getLeftJoyStick() {
		return leftJoystick;
	}

	@Override
	public void setRightJoystick(double value) {
		rightJoystick = value;
	}

	@Override
	public void setLeftJoystick(double value) {
		leftJoystick = value;
	}

}
