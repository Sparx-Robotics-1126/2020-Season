package frc.climbing.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.climbing.TestClimbingSensors;


public class WinchCommandTest{
	
	@Test
	public void underDistance_ShouldContinueToWinch() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.winchDistance = 0;
		ClimbingCommand command = new StartWinch(sensors, 10);
		ClimbingOutput output = command.execute();
		assertEquals(1, output.getOutput(), 0.0001);
	}
	
	@Test
	public void atDistance_ShouldStopWinch() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.winchDistance = 10;
		ClimbingCommand command = new StartWinch(sensors, 10);
		ClimbingOutput output = command.execute();
		assertEquals(0, output.getOutput(), 0.0001);
	}
	
	@Test
	public void overDistance_ShouldStopWinch() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.winchDistance = 11;
		ClimbingCommand command = new StartWinch(sensors, 10);
		ClimbingOutput output = command.execute();
		assertEquals(0, output.getOutput(), 0.0001);
	}
	
}
