package frc.shooter.command;

import static org.junit.Assert.*;

import org.junit.Test;
import frc.drives.DrivesTestSensors;
import frc.shooter.ShooterOutput;
import frc.shooter.command.ShooterSpeed;
import frc.shooter.MathHelpers;
import frc.shooter.ShooterSensorsInterfeace;

public class ShooterSpeedTest {
	
	@Test
	public void isReadyToShootReturnsTrueWhenCurrentIsDesired() {
		
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		assertEquals("isReadyToShoot", true, shooter.isReadyToShoot(1.00, 1.00));
	}

	@Test
	public void isReadyToShootReturnsTrueWhenCurrentIs1p9PercentLow() {
		
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		assertEquals("isReadyToShoot", true, shooter.isReadyToShoot(0.981, 1.00));
	}

	@Test
	public void isReadyToShootReturnsFalseWhenCurrentIs2p1PercentLow() {
		
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		assertEquals("isReadyToShoot", false, shooter.isReadyToShoot(0.979, 1.00)); 
	}
	
	@Test
	public void isReadyToShootReturnsTrueWhenCurrentIs1p9PercentHigh() {
		
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		assertEquals("isReadyToShoot", true, shooter.isReadyToShoot(1.019, 1.00));
	}

	@Test
	public void isReadyToShootReturnsFalseWhenCurrentIs2p1PercentHigh() {
		
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		assertEquals("isReadyToShoot", false, shooter.isReadyToShoot(1.021, 1.00));
	}
	
	boolean isAccelerating; 
	
	@Test
	public void increasesSpeedWhenBelowTargetValue() {
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		shooterSensor.shooterSpeed = 2;
		shooterSensor.distanceFromTarget = 40;
		
		ShooterOutput output = shooter.execute();
		output = shooter.execute();
		
		if (output.getOutputValue() > 0.5) {
			isAccelerating = true;
		}
		else {
			isAccelerating = false;
		}
		
		assertTrue("Returned power is higher than starting power", isAccelerating);
	}

	
	private boolean isBelowStartingSpeed; 
	
		@Test
	public void DecelerateAfterOvershooting() {
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		shooterSensor.distanceFromTarget = 1;
		shooterSensor.shooterSpeed = 100;
		
		
		ShooterOutput output = shooter.execute();
		output = shooter.execute();
		
		
		if (output.getOutputValue() < 0.5) {
			this.isBelowStartingSpeed = true;
		}
		else {
			this.isBelowStartingSpeed = false;
		}
		
		
		assertTrue("Power output is below starting output", isBelowStartingSpeed);
		assertEquals("Is ready to shoot", false, output.isReadyToShoot()); 
	}
		
	@Test
	public void DoesNotReturnValueHigherThanOne() {
		DrivesTestSensors sensors = new DrivesTestSensors();
		ShooterTestSensor shooterSensor = new ShooterTestSensor();
		shooterSensor.shooterSpeed = 10;
		shooterSensor.distanceFromTarget = 25;
		ShooterSpeed shooter = new ShooterSpeed(shooterSensor, sensors);
		
		ShooterOutput output = shooter.execute();
		for (int i = 0; i < 10; i ++) {
			output = shooter.execute();
		}
		
		assertEquals("Power output", 1, output.getOutputValue(), 0.0001);
		assertEquals("Is ready to shoot", false, output.isReadyToShoot());
	}
}
