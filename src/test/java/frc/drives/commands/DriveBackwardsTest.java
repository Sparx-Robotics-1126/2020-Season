package frc.drives.commands;

import static org.junit.Assert.*;
import frc.drives.DrivesOutput;
import frc.drives.DrivesTestSensors;

public class DriveBackwardsTest {

	private DrivesTestSensors sensors;

	public void setup() {
		sensors = new DrivesTestSensors();

	}

	public void motorsAreEqual() {

		DriveBackwards backwardC = new DriveBackwards(sensors, 0.5, 10);
		DrivesOutput output = backwardC.execute();

		assertEquals("motors are not the same speed", output.getRightMotor(), output.getLeftMotor(), 0.001);
	}
	public void leftMotorStop() {

		DriveBackwards backwardC = new DriveBackwards(sensors, 0.5, 10);
		sensors.setLeftEncoderDistance(9.5);
		sensors.setRightEncoderDistance(10);
		DrivesOutput output = backwardC.execute();

		assertEquals("left motor should stop when left motor reaches distance", 0, output.getLeftMotor(), 0.001);
		assertEquals("right motor should stop when left motor reaches distance", 0, output.getRightMotor(), 0.001);
	}
	public void RightMotorStop() {

		DriveBackwards backwardC = new DriveBackwards(sensors, 0.5, 10);
		sensors.setRightEncoderDistance(9.5);
		sensors.setLeftEncoderDistance(10); 
		DrivesOutput output = backwardC.execute();

		assertEquals("right motor should stop when right motor reaches distance", 0, output.getRightMotor(), 0.001);
		assertEquals("left motor should stop when right motor reaches distance", 0, output.getLeftMotor(), 0.001);
	}
	public void LeftMotorLessPower() {
		sensors.setGyroAngle(1);
		DriveBackwards backwardC = new DriveBackwards(sensors, 0.5, 10);
		sensors.setGyroAngle(0);
		DrivesOutput output = backwardC.execute();
		assertEquals("left motor power should be 0.5", 0.5, output.getLeftMotor(), 0.001);
		assertEquals("right motor power should be 0.5", 0.5, output.getRightMotor(), 0.001);
	}
	public void RightMotorLessPower() {
		sensors.setGyroAngle(-1);
		DriveBackwards backwardC = new DriveBackwards(sensors, 0.5, 10);
		sensors.setGyroAngle(0);
		DrivesOutput output = backwardC.execute();

		assertEquals("Right motor power should be 0.5", 0.5, output.getRightMotor(), 0.001);
		assertEquals("Left motor power should be 0.5", 0.5, output.getLeftMotor(), 0.001);
	}
} 