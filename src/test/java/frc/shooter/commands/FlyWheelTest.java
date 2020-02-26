package frc.shooter.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesTestSensors;
import frc.health.HealthReport;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;

public class FlyWheelTest {
	
	ShooterTestSensor sensors = new ShooterTestSensor();
	DrivesTestSensors driveSensors = new DrivesTestSensors();
	
	@Test
	public void TestFlyWheelReturns45() {
		ShooterCommand command = new TestFlywheel(null, null);
		ShooterOutput output = command.execute();
		assertEquals(20, output.getOutputValue(), 0.0001);
	}
	
	@Test
	public void IfSpeedChangingReturnsNoError() {
		TestFlywheel flywheel = new TestFlywheel(sensors, driveSensors);
		
		HealthReport report = flywheel.checkHealth();
		
		assertEquals(true, report.isError());
		
		sensors.shooterSpeed = 10;
		
		report = flywheel.checkHealth();
		
		assertEquals(false, report.isError());
	}
}
