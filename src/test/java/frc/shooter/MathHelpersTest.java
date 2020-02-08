package frc.shooter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathHelpersTest {
	
	@Test
	public void TestShooterOffset () {
		double output = MathHelpers.getShootOffset(120, 200, 20);
		assertEquals(4.715, output, 0.01);
		
	}
}
