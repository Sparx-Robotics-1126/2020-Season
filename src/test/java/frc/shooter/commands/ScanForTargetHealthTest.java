package frc.shooter.commands;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import frc.shooter.commands.ScanForTarget;
import frc.drives.DrivesTestSensors;
import frc.health.HealthReport;

public class ScanForTargetHealthTest {
	
	ShooterTestSensor shooterSensor = new ShooterTestSensor();
	DrivesTestSensors drivesSensor = new DrivesTestSensors();
	ScanForTarget healthScanner;
	HealthReport report;
	
	//Shooter is turning Right, and it should be; should not error
	@Test
	public void ReturnTrueIfTurretSpinsRightWhenItShould() {
		healthScanner = new ScanForTarget(shooterSensor, drivesSensor);
		shooterSensor.shooterAngle = 108;
		healthScanner.checkHealth();
		shooterSensor.shooterAngle = 109;
		
		report = healthScanner.checkHealth();
		
		assertEquals("Returning error when it should not", false, report.isError());
		assertEquals("Returning error message when it should not", "Good", report.getMessage());
	}
	
	//Shooter is turning left, and it should be; should not error
		@Test
		public void ReturnTrueIfTurretSpinsLeftWhenItShould() {
			shooterSensor.shooterAngle = 150;
			healthScanner = new ScanForTarget(shooterSensor, drivesSensor);
			healthScanner.execute();
			shooterSensor.shooterAngle = 140;
			
			report = healthScanner.checkHealth();
			
			assertEquals("Returning error when it should not", false, report.isError());
			assertEquals("Returning error message when it should not", "Good", report.getMessage());
		}
	
	//Shooter should be turning right, but is turning left; should error
	@Test
	public void returnsCorrectFailReportIfTestForSpinnningCorrectDirectionFails() {
		healthScanner = new ScanForTarget(shooterSensor, drivesSensor);
		
		shooterSensor.shooterAngle = 60;
		healthScanner.checkHealth();
		shooterSensor.shooterAngle = 50;
		
		report = healthScanner.checkHealth();
		
		assertEquals("Returning no error when it should send one", true, report.isError());
		assertEquals("Does not send correct error message:", "Turret is not turning in correct direction", report.getMessage());
	}
	
	//shooter should be turning right, but is not turning; should error
	@Test
	public void returnsCorrectFailReportMessageIfNotSpinning() {
		healthScanner = new ScanForTarget(shooterSensor, drivesSensor);
		shooterSensor.shooterAngle = 0;
		report = healthScanner.checkHealth();
		
		assertEquals("Returning no error when it should send one", true, report.isError());
		assertEquals("Does not send correct error message:", "Turret is not spinning", report.getMessage());
	}
	
}
