package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class DPadButton extends Button {
	
	public DPadButton(Joystick joystick, int button) {
		super(joystick, button);
		
	}
	@Override
	public boolean get() {
		return false;
	}
}
