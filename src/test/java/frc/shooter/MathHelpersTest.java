package frc.shooter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathHelpersTest {
	
	@Test
	public void TestShooterOffset () {
		double output = MathHelpers.getShootOffset(45, 200, 12);
		assertEquals(15, output, 0.01);
		
	}
}
