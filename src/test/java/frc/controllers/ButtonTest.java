package frc.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import frc.controllers.Button.ButtonType;

public class ButtonTest {
	
	@Test
	public void risingEdge_ShouldOnlyReturnTrueOnFirstPressed() {
		Button button = new Button(null, 0, ButtonType.RISING_EDGE);
		assertEquals(false, button.getTriggered(false, false));//No press
		assertEquals(true, button.getTriggered(true, false));//first press
		assertEquals(false, button.getTriggered(true, true));//kept pressed
		assertEquals(false, button.getTriggered(false, true));//Let go of button
	}
	
	@Test
	public void fallingEdge_ShouldOnlyReturnTrueOnFirstUnpressed() {
		Button button = new Button(null, 0, ButtonType.FALLING_EDGE);
		assertEquals(false, button.getTriggered(false, false));//No press
		assertEquals(false, button.getTriggered(true, false));//first press
		assertEquals(false, button.getTriggered(true, true));//kept pressed
		assertEquals(true, button.getTriggered(false, true));//Let go of button
	}
	
	@Test
	public void pressed_ShouldOnlyReturnTrueWhenPressed() {
		Button button = new Button(null, 0, ButtonType.PRESSED);
		assertEquals(false, button.getTriggered(false, false));//No press
		assertEquals(true, button.getTriggered(true, false));//first press
		assertEquals(true, button.getTriggered(true, true));//kept pressed
		assertEquals(false, button.getTriggered(false, true));//Let go of button
	}

}
