package frc.storage.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.health.HealthCheck;
import frc.health.HealthReport;
import frc.storage.StorageOutput;
import frc.storage.StorageTestSensor;
import frc.storage.command.PrepareForShooting;

public class PrepareForShootingTest {
	
	@Test
    public void ballNotAtTop_ShouldRunBelts(){
        StorageTestSensor tsa = new StorageTestSensor();
        PrepareForShooting command = new PrepareForShooting(tsa, (short)1);
        StorageOutput output = command.execute();
        assertEquals(true, 0 < output.getPrimaryOutput());
        assertEquals(true, 0 < output.getSecondaryOutput());
	}
	
	@Test
    public void ballAtTop_ShouldRunBelts(){
        StorageTestSensor tsa = new StorageTestSensor();
        tsa.testShootSensor = true;
        PrepareForShooting command = new PrepareForShooting(tsa, (short)1);
        StorageOutput output = command.execute();
        assertEquals(0, output.getPrimaryOutput(), 0.00001);
        assertEquals(0, output.getSecondaryOutput(), 0.00001);
	}
	
	@Test
    public void healthCheck_noIntakeError(){
        StorageTestSensor tsa = new StorageTestSensor();
        PrepareForShooting command = new PrepareForShooting(tsa, (short)1);
        HealthReport report = command.checkHealth();
        assertEquals(true, report.isError());
        assertEquals("Waiting for Intake...", report.getMessage());
	}
	
	@Test
    public void healthCheck_noIndexError(){
        StorageTestSensor tsa = new StorageTestSensor();
        tsa.testIntakeSensor = true;
        PrepareForShooting command = new PrepareForShooting(tsa, (short)1);
        HealthReport report = command.checkHealth();
        assertEquals(true, report.isError());
        assertEquals("Waiting for Index...", report.getMessage());
	}
	
	@Test
    public void healthCheck_noShooterError(){
        StorageTestSensor tsa = new StorageTestSensor();
        tsa.testIntakeSensor = true;
        tsa.testIndexSensor = true;
        PrepareForShooting command = new PrepareForShooting(tsa, (short)1);
        HealthReport report = command.checkHealth();
        assertEquals(true, report.isError());
        assertEquals("Waiting for Shooter...", report.getMessage());
	}
	
	@Test
    public void healthCheck_noError(){
        StorageTestSensor tsa = new StorageTestSensor();
        tsa.testIntakeSensor = true;
        tsa.testIndexSensor = true;
        tsa.testShootSensor = true;
        PrepareForShooting command = new PrepareForShooting(tsa, (short)1);
        HealthReport report = command.checkHealth();
        assertEquals(false, report.isError());
	}

}
