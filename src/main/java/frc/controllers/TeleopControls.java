package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import frc.subsystem.Drives;

public class TeleopControls extends Controller {

	private Button spinButton;
    private Axis axis1;
    private Axis axis2;
	
	/**
	 * Constructor - created by SubsystemManager.java
	 * @param drives - Drives Subsystem
	 */
    public TeleopControls(Drives drives){
        super(drives); //Superclass stores for you!!
        
        Joystick joystick = new Joystick(0);
        spinButton = new Button(joystick, 0);//Creates button for keeping track of button 0
        axis1 = new Axis(joystick, 0, false);
        axis2 = new Axis(joystick, 0, false);
    }

    @Override
    public void execute() {
    	if(spinButton.get()) {
    		drives.startSpin();
    	}
    	drives.setJoysticks(axis1.get(), axis2.get());
    }
}
