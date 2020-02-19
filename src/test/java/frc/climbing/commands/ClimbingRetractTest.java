 package frc.climbing.commands;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import frc.climbing.ClimbingOutput;
import frc.climbing.TestClimbingSensors;
import frc.climbing.command.ClimbingRetract;

public class ClimbingRetractTest {
	
	@Test
	public void AtWantedDistance() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.leadScrewDistance = 0.25;
		ClimbingOutput output = new ClimbingRetract(sensors).execute();
		assertEquals(0 , output.getOutput() , 0.00001 );
	}
	
	@Test
	public void NotAtWantedDistance() {
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.leadScrewDistance = 3;
		ClimbingOutput output = new ClimbingRetract(sensors).execute();
		assertEquals(-1 , output.getOutput() , 0.00001 );
	}
}
