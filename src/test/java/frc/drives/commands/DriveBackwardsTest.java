package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frc.drives.DrivesOutput;
import frc.drives.DrivesTestSensors;
import frc.health.HealthReport;

public class DriveBackwardsTest {

	private DrivesTestSensors sensors;

	@Before
	public void setup() {
		sensors = new DrivesTestSensors();
	}

	@Test
	public void motorsAreEqual() {
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		DrivesOutput output = backwardC.execute();
		assertEquals("motors are not the same speed", output.getRightMotor(), output.getLeftMotor(), 0.001);
	}

	@Test
	public void pointedRight_ShouldSlowLeft() {
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(5);
		DrivesOutput output = backwardC.execute();

		assertTrue("Front pointed right should slow down right", output.getRightMotor() < output.getLeftMotor());//Negative Numbers
	}

	@Test
	public void pointedLeft_ShouldSlowRight() {
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(-5);
		DrivesOutput output = backwardC.execute();

		assertTrue("Front pointed right should slow down right", output.getRightMotor() > output.getLeftMotor());//Negative Numbers
	}

	@Test
	public void atDistance_ShouldStop(){
		sensors.setLeftEncoderDistance(0);
		sensors.setRightEncoderDistance(0);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		DrivesOutput firstOutput = backwardC.execute();
		assertTrue(firstOutput.getLeftMotor() < 0);
		assertTrue(firstOutput.getRightMotor() < 0);

		sensors.setLeftEncoderDistance(-5);
		sensors.setRightEncoderDistance(-5);
		DrivesOutput secondOutput = backwardC.execute();
		assertTrue(secondOutput.getLeftMotor() < 0);
		assertTrue(secondOutput.getRightMotor() < 0);
		assertTrue(firstOutput.getLeftMotor() < secondOutput.getLeftMotor());//Negative Numbers
		assertTrue(firstOutput.getRightMotor() < secondOutput.getRightMotor());//Negative Nubmers

		sensors.setLeftEncoderDistance(-10);
		sensors.setRightEncoderDistance(-10);
		DrivesOutput lastOutput = backwardC.execute();
		assertEquals(0, lastOutput.getLeftMotor(), 0);
		assertEquals(0, lastOutput.getRightMotor(), 0);
	}
	
	@Test
	public void isLeftEncoderMovingInWrongDirection() {
		sensors.setLeftEncoderDistance(10);
		sensors.setRightEncoderDistance(10);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(11);
		sensors.setRightEncoderDistance(9);
		backwardC.execute();
		HealthReport report = backwardC.checkHealth();
		assertEquals(true, report.isError());
		assertEquals("Left encoder is moving in the wrong direction!", report.getMessage());
	}
	
	@Test
	public void isRightEncoderMovingInWrongDirection() {
		sensors.setLeftEncoderDistance(10);
		sensors.setRightEncoderDistance(10);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(9);
		sensors.setRightEncoderDistance(11);
		backwardC.execute();
		HealthReport report = backwardC.checkHealth();
		assertEquals(true, report.isError());
		assertEquals("Right encoder is moving in the wrong direction!", report.getMessage());
	}
	
	@Test
	public void isEncodersMovingInRightDirection() {
		sensors.setLeftEncoderDistance(10);
		sensors.setRightEncoderDistance(10);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(9);
		sensors.setRightEncoderDistance(9);
		backwardC.execute();
		HealthReport report = backwardC.checkHealth();
		assertEquals(false, report.isError());
		assertEquals("Good", report.getMessage());
	}
	
	@Test
	public void isRobotBelowExpectedAngleRange() {
		sensors.setGyroAngle(10);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setGyroAngle(7);
		backwardC.execute();
		HealthReport report = backwardC.checkHealth();
		assertEquals(true, report.isError());
		assertEquals("Robot is below the expected angle range!", report.getMessage());
	}
	
	@Test
	public void isRobotAboveExpectedAngleRange() {
		sensors.setGyroAngle(10);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setGyroAngle(13);
		backwardC.execute();
		HealthReport report = backwardC.checkHealth();
		assertEquals(true, report.isError());
		assertEquals("Robot is above the expected angle range!", report.getMessage());
	}
	
	@Test
	public void isRobotInsideLowExpectedAngleRange() {
		sensors.setGyroAngle(10);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setGyroAngle(8);
		backwardC.execute();
		HealthReport report = backwardC.checkHealth();
		assertEquals(false, report.isError());
		assertEquals("Good", report.getMessage());
	}
	
	@Test
	public void isRobotInsideHighExpectedAngleRange() {
		sensors.setGyroAngle(10);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setGyroAngle(12);
		backwardC.execute();
		HealthReport report = backwardC.checkHealth();
		assertEquals(false, report.isError());
		assertEquals("Good", report.getMessage());
	}

} 