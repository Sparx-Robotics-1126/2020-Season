package frc.climbing.commands;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.climbing.TestClimbingSensors;
import frc.climbing.commands.ExtendScissorLift;

public class TestExtendScissorLift {
	
	
	@Test
	public void underBar() {
		
		TestClimbingSensors sensor = new TestClimbingSensors();
		sensor.leadScrewDistance = 1;
		sensor.isTouchingBar = false;
		ExtendScissorLift extending = new ExtendScissorLift(sensor, 40);
		ClimbingOutput output = extending.execute();
		assertEquals(output.getOutput(), 0.5,0);
	}
	
	@Test
	public void atBar() {
		TestClimbingSensors sensor = new TestClimbingSensors();
		sensor.leadScrewDistance = 4;
		sensor.isTouchingBar = true;
		ExtendScissorLift extending = new ExtendScissorLift(sensor, 40);
		ClimbingOutput output = extending.execute();
		assertEquals(output.getOutput(), 0,0);

	}
	
	@Test
	public void overBar() {
		TestClimbingSensors sensor = new TestClimbingSensors();
		sensor.leadScrewDistance = 44;
		sensor.isTouchingBar = false;
		ExtendScissorLift extending = new ExtendScissorLift(sensor, 40);
		ClimbingOutput output = extending.execute();
		assertEquals(output.getOutput(), 0,0);

	}
	
	

}
