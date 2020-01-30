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
		DrivesOutput output = forwardC.execute();
		sensors.setLeftEncoderDistance(10);
		sensors.setRightEncoderDistance(9.5);
		output = forwardC.execute();
		
		//left motor should stop when left motor reaches distance
		assertEquals("left motor should stop when left motor reaches distance", 0, output.getLeftMotor(), 0.001);
		//right motor should stop when left motor reaches distance
		assertEquals("right motor should stop when left motor reaches distance", 0, output.getRightMotor(), 0.001);
	}
	@Test
	//if Right motor reaches distance, STOP THEM MOTORS
	public void RightMotorWillStopWhenDistanceReachedTest() {
		
		DriveForward forwardC = new DriveForward(sensors, 0.5, 10);
		DrivesOutput output = forwardC.execute();
		sensors.setRightEncoderDistance(10);
		sensors.setLeftEncoderDistance(9.5);
		output = forwardC.execute();
		
		//left motor should stop when left motor reaches distance
		assertEquals("Right motor should stop when Right motor reaches distance", 0, output.getRightMotor(), 0.001);
		//right motor should stop when left motor reaches distance
		assertEquals("Left motor should stop when Right motor reaches distance", 0, output.getLeftMotor(), 0.001);
	}
}