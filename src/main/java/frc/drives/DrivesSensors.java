package frc.drives;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.IO;

public class DrivesSensors implements DrivesSensorInterface {

	private AHRS gyro;
	private CANEncoder rightEncoder;
	private CANEncoder leftEncoder;
	private double rightJoystick;
	private double leftJoystick;
	
	public DrivesSensors() {
		gyro = new AHRS(SerialPort.Port.kUSB);
		rightJoystick = 0;
		leftJoystick = 0;
	}
	
	@Override
	public void addEncoders(CANEncoder leftSpark, CANEncoder rightSpark) {
		this.leftEncoder = leftSpark;
		this.rightEncoder = rightSpark;
	}
	
	@Override
	public double getLeftEncoderDistance() {
		return leftEncoder.getPosition();
	}

	@Override
	public double getLeftEncoderSpeed() {
		return leftEncoder.getVelocity();
	}

	@Override
	public double getRightEncoderDistance() {
		return rightEncoder.getPosition();
	}

	@Override
	public double getRightEncoderSpeed() {
		return rightEncoder.getVelocity();
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
