package frc.climbing.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import frc.climbing.TestClimbingSensors;
import frc.health.HealthReport;

public class TestWinchTest {

	@Test
	public void isMovingTheRIghtWay() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.winchDistance = 10;
		TestWinch test = new TestWinch(sensors);
		sensors.winchDistance = 8;
		test.execute();
		
		HealthReport output = test.checkHealth();
		
		assertEquals(true, output.isError());
		assertEquals("Winch is moving the wrong way!", output.getMessage());
	}
	
	@Test
	public void isNotChanging() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.winchDistance = 10;
		TestWinch test = new TestWinch(sensors);
		sensors.winchDistance = 10;
		test.execute();
		
		HealthReport output = test.checkHealth();
		
		assertEquals(true, output.isError());
		assertEquals("Winch is not moving!", output.getMessage());
	}
	
	@Test
	public void isGud() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.winchDistance = 10;
		TestWinch test = new TestWinch(sensors);
		sensors.winchDistance = 12;
		test.execute();
		
		HealthReport output = test.checkHealth();
		
		assertEquals(false, output.isError());
	}

}
