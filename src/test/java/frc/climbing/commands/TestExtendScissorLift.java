package frc.climbing.commands;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.climbing.TestClimbingSensors;
import frc.climbing.commands.ExtendScissorLift;
import frc.health.HealthCheck;
import frc.health.HealthReport;

public class TestExtendScissorLift {
	
	
	@Test
	public void underBar() {
		
		TestClimbingSensors sensor = new TestClimbingSensors();
		sensor.leadScrewDistance = 1;
		sensor.isTouchingBar = false;
		ExtendScissorLift extending = new ExtendScissorLift(sensor, 40);
		ClimbingOutput output = extending.execute();
		assertEquals(1, output.getOutput(), 0.00001);
	}
	
	@Test
	public void atBar() {
		TestClimbingSensors sensor = new TestClimbingSensors();
		sensor.leadScrewDistance = 4;
		sensor.isTouchingBar = true;
		ExtendScissorLift extending = new ExtendScissorLift(sensor, 40);
		ClimbingOutput output = extending.execute();
		assertEquals(0, output.getOutput(), 0.00001);

	}
	
	@Test
	public void overBar() {
		TestClimbingSensors sensor = new TestClimbingSensors();
		sensor.leadScrewDistance = 44;
		sensor.isTouchingBar = false;
		ExtendScissorLift extending = new ExtendScissorLift(sensor, 40);
		ClimbingOutput output = extending.execute();
		assertEquals(0, output.getOutput(), 0.00001);

	}

	@Test
	public void health(){
		TestClimbingSensors sensors = new TestClimbingSensors();
		sensors.leadScrewDistance = 15;
		sensors.isTouchingBar = false;
		ExtendScissorLift extending = new ExtendScissorLift(sensors, 20);

		HealthReport hc = extending.checkHealth();
		assertEquals(false, hc.isError());
		assertEquals(hc.getMessage(),"Lead screw encoder went 15.0");
	}  
	 

	
}
