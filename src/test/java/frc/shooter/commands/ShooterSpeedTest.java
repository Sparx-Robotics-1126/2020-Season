package frc.shooter.commands;

import static org.junit.Assert.*;

import org.junit.Test;
import frc.drives.DrivesTestSensors;
import frc.shooter.MathHelpers;
//import frc.sensors.Limelight;
import frc.shooter.ShooterOutput;

public class ShooterSpeedTest {

	@Test
	public void ReturnMathRPS_NotReadyToShoot() {
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		shooterSensor.distanceToTarget = 120;
		shooterSensor.shooterSpeed = 10;
		double expectedRPS = MathHelpers.getShootingSpeed(120);
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		ShooterOutput output = shooter.execute();
		
		assertEquals("RPS Check", expectedRPS, output.getOutputValue(), 0.001);
		assertEquals("Is ready to shoot", false, output.isReadyToShoot());
	}
	@Test
	public void ReturnMathRPS_ReadyToShoot() {
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		double expectedRPS = MathHelpers.getShootingSpeed(120);
		shooterSensor.distanceToTarget = 120;
		shooterSensor.shooterSpeed = expectedRPS;
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		ShooterOutput output = shooter.execute();
		
		assertEquals("RPS Check", expectedRPS, output.getOutputValue(), 0.001);
		assertEquals("Is ready to shoot", true, output.isReadyToShoot());
	}
}
