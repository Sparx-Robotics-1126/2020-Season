package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class Button {
	
	enum ButtonType{
		RISING_EDGE,
		FALLING_EDGE
	}
	
	private Joystick joystick;
	private int button;
	private ButtonType buttonType;
	
	public Button(Joystick joystick, int button) {
		this.joystick = joystick;
		this.button = button;
//		this.buttonType = DEFAULT?
	}
	
	public Button(Joystick joystick, int button, ButtonType type) {
		this.joystick = joystick;
		this.button = button;
		this.buttonType = type;
	}
	
	/**
	 * Includes falling/rising feature
	 * @return value of specified button
	 */
	public boolean get() {
		if (buttonType.equals(ButtonType.RISING_EDGE)) {
			joystick.getRawButtonReleased(button);
			buttonType = ButtonType.FALLING_EDGE;
			return true;
		} else {
			joystick.getRawButtonPressed(button);
			buttonType = ButtonType.RISING_EDGE;
			return false;
		}
	}
 
}
