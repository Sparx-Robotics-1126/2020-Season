package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class TeleopControls extends Controller {

	private Axis axis1;
    private Axis axis2;
	
	private Button moveForwardButton;
	private Button backwardButton;
	private Button moveRight;
	private Button buttonLeft;
	
	/**
	 * Constructor - created by SubsystemManager.java
	 * @param drives - Drives Subsystem
	 */
    public TeleopControls(Acquisitions acq, Drives drives, Shooter shooter, Storage storage){
        super(acq, drives, shooter, storage); //Superclass stores for you!!
        
        Joystick joystick = new Joystick(0);

        axis1 = new Axis(joystick, 5, true);
        axis2 = new Axis(joystick, 1, true);
        
        moveForwardButton = new Button(joystick, 1);
        backwardButton = new Button(joystick, 2);
        moveRight = new Button(joystick, 4);
		buttonLeft = new Button(joystick, 3);
		
    }

    @Override
    public void execute() {
    	drives.setJoysticks(axis1.get(), axis2.get());
    	if(backwardButton.get()) {
    		drives.moveBackward(5);
    	}
    	if(moveForwardButton.get()) {
    		drives.moveForward(5); //in feet
    	}
    	if(moveRight.get()) {
    		drives.turnRight(90);
    	}
    	if(buttonLeft.get()) {
			drives.turnLeft(90);
		}
    } 
}