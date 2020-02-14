package ClimbingTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.climbing.StartWinch;


public class WinchCommandTest{
	
	private ClimbingTestSensors sensors;

	@Before
	public void setup() {
		sensors = new ClimbingTestSensors();
	}
	@Test
	public void TestWinchCommand() {
		ClimbingCommand command = new StartWinch(sensors,true);
		ClimbingOutput output = command.execute();
		assertEquals(0.3, output.getOutput(), 0.0001);
		
	}
}
