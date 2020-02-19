package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class Button {
	
	enum ButtonType{
		RISING_EDGE, // Initial Click
		FALLING_EDGE, // After Pressed and Released
		PRESSED
	}
	
	private Joystick joystick;
	private int button;
	private ButtonType buttonType;
	private boolean buttonPreviouslyPressed;
	
	public Button(Joystick joystick, int button) {
		this.joystick = joystick;
		this.button = button;
		this.buttonType = ButtonType.RISING_EDGE;
		buttonPreviouslyPressed = false;
	}
	
	public Button(Joystick joystick, int button, ButtonType type) {
		this.joystick = joystick;
		this.button = button;
		this.buttonType = type;
		buttonPreviouslyPressed = false;
	}
	
	/**
	 * Includes falling/rising feature
	 * @return value of specified button
	 */
	public boolean get() {
		boolean isCurrentlyPressed = joystick.getRawButton(button);
		boolean trigger = getTriggered(isCurrentlyPressed, buttonPreviouslyPressed);
		buttonPreviouslyPressed = isCurrentlyPressed;
		return trigger;
	}
	
	/**
	 * THIS IS FOR INTERNAL USE AND TESTING ONLY
	 * DO NOT CALL!!!!
	 */
	protected boolean getTriggered(boolean isCurrentlyPressed, boolean buttonPreviouslyPressed) {
		if (buttonType == ButtonType.RISING_EDGE) {
			return isCurrentlyPressed && !buttonPreviouslyPressed;//Pressed now, wasn't pressed before
		} else if(buttonType == ButtonType.FALLING_EDGE) {
			return !isCurrentlyPressed && buttonPreviouslyPressed;//Not pressed now, was pressed before
		}else {
			return isCurrentlyPressed;
		}
	}
 
}
