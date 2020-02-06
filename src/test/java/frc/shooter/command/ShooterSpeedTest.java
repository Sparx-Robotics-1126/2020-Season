package frc.shooter.command;

import static org.junit.Assert.*;

import org.junit.Test;
import frc.drives.DrivesTestSensors;
//import frc.sensors.Limelight;
import frc.shooter.ShooterOutput;

public class ShooterSpeedTest {

	@Test
	public void ReturnFullSpeedIfStillAccelerating() {
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		shooterSensor.shooterSpeed = 9;
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		ShooterOutput output = shooter.execute();
		
		assertEquals("Testing output", 1.0, output.getOutputValue(), 0.001);
		assertEquals("Is ready to shoot", false, output.isReadyToShoot());
	}
	@Test
	public void ReturnReducedSpeedWhenCloseToTarget() {
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		shooterSensor.shooterSpeed = 9.8;
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		ShooterOutput output = shooter.execute();
		output = shooter.execute();
		
		assertEquals("Testing output", 1.0, output.getOutputValue(), 0.001);
		assertEquals("Is ready to shoot", false, output.isReadyToShoot());
	}
}
