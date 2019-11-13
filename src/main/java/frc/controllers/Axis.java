package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;

/**
 * Getter for Joystick Axis
 */
public class Axis {
	
	private Joystick joystick;
	private int  axis;
	private boolean invert;
	
	public Axis(Joystick joystick, int axis, boolean invert) {
		this.joystick = joystick;
		this.axis = axis;
		this.invert = invert;
	}

	/**
	 * Add Deadband to remove false movement
	 * @return value of specified axis
	 */
	public double get() {
		//TODO: get axis
		return 0;
	}
	
}
