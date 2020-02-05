package frc.auto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAutoRoutine {

	@Test
	public void creatingRoutine_ShouldSetAllValues() {
		TestRoutine testRoutine = new TestRoutine();
		assertEquals("Test Routine", testRoutine.getAutoName());
		assertEquals(AutoFeature.STOP, testRoutine.getAutoSequence()[0].getFeature());
		assertEquals(AutoFeature.STOP, testRoutine.getAutoSequence()[1].getFeature());
		assertEquals(254, testRoutine.getAutoSequence()[1].value1, 0.0001);
		assertEquals(AutoFeature.STOP, testRoutine.getAutoSequence()[2].getFeature());
		assertEquals(1114, testRoutine.getAutoSequence()[2].value1, 0.0001);
		assertEquals(2054, testRoutine.getAutoSequence()[2].value2, 0.0001);
	}
	
	public static class TestRoutine extends AutoRoutine {

		private static final AutoTask[] auto = {
				AutoTask.createTask(AutoFeature.STOP),
				AutoTask.createTask(AutoFeature.STOP, 254),
				AutoTask.createTask(AutoFeature.STOP, 1114, 2054)
		};
		
		public TestRoutine() {
			super("Test Routine", auto);
		}
		
	}
}
