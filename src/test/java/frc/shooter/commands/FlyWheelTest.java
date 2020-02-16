package frc.shooter.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;

public class FlyWheelTest {
	
	@Test
	public void TestFlyWheelReturns45() {
		ShooterCommand command = new TestFlywheel(null, null);
		ShooterOutput output = command.execute();
		assertEquals(20, output.getOutputValue(), 0.0001);
	}
}
