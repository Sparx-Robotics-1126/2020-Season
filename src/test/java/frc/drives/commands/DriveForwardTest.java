package frc.drives.commands;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesTestSensors;

public class DriveForwardTest {
	
	private DrivesTestSensors sensors;
	
	@Before
	public void setup() {
		sensors = new DrivesTestSensors();
		
	}
	
	@Test
	//if gyro centered, both motors be equal.
	public void equalMotorWhenGyroCenteredTest() {
		
		DriveForward forwardC = new DriveForward(sensors, 0.5, 10);
		DrivesOutput output = forwardC.execute();
		
		//making sure both motors are spinning at the same SPEED
		assertEquals("motors are not the same speed", output.getRightMotor(), output.getLeftMotor(), 0.001);
	}
	@Test
	//if left motor reaches distance, STOP THEM MOTORS
	public void leftMotorWillStopWhenDistanceReachedTest() {
		
		DriveForward forwardC = new DriveForward(sensors, 0.5, 10);
		sensors.setLeftEncoderDistance(10);
		sensors.setRightEncoderDistance(9.5);
		DrivesOutput output = forwardC.execute();
		
		//left motor should stop when left motor reaches distance
		assertEquals("left motor should stop when left motor reaches distance", 0, output.getLeftMotor(), 0.001);
		//right motor should stop when left motor reaches distance
		assertEquals("right motor should stop when left motor reaches distance", 0, output.getRightMotor(), 0.001);
	}
	@Test
	//if Right motor reaches distance, STOP THEM MOTORS
	public void RightMotorWillStopWhenDistanceReachedTest() {
		
		DriveForward forwardC = new DriveForward(sensors, 0.5, 10);
		sensors.setRightEncoderDistance(10);
		sensors.setLeftEncoderDistance(9.5); 
		DrivesOutput output = forwardC.execute();
		
		//left motor should stop when left motor reaches distance
		assertEquals("Right motor should stop when Right motor reaches distance", 0, output.getRightMotor(), 0.001);
		//right motor should stop when left motor reaches distance
		assertEquals("Left motor should stop when Right motor reaches distance", 0, output.getLeftMotor(), 0.001);
	}
	@Test
	//gyro angle is greater than 0, then left motor should receive less power
	public void LeftMotorWillReceiveLessPowerWhenGyroAngleIsGreaterThan0() {
		sensors.setGyroAngle(0);
		DriveForward forwardC = new DriveForward(sensors, 0.5, 10);
		sensors.setGyroAngle(1);
		DrivesOutput output = forwardC.execute();
		
		//left motor power should be 0.45 if the gyro angle is greater than 0
		assertEquals("LeftMoor power should be 0.5", 0.5, output.getLeftMotor(), 0.001);
		//right motor power should be .5 if the gyro angle is greater than 0
		assertEquals("right motor power should be 0.5", 0.5, output.getRightMotor(), 0.001);
	}
	@Test
	//gyro angle is greater Less 0, then Right motor should receive less power
	public void RightMotorWillReceiveLessPowerWhenGyroAngleIsLessThan0() {
		sensors.setGyroAngle(0);
		DriveForward forwardC = new DriveForward(sensors, 0.5, 10);
		sensors.setGyroAngle(-1);
		DrivesOutput output = forwardC.execute();
		
		//Right motor power should be 0.45 if the gyro angle is Less than 0
		assertEquals("Right motor power should be 0.5", 0.5, output.getRightMotor(), 0.001);
		//Left motor power should be .5 if the gyro angle is Less than 0
		assertEquals("Left motor power should be 0.5", 0.5, output.getLeftMotor(), 0.001);
	}
}