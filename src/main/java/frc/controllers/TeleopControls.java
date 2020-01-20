package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class TeleopControls extends Controller {

	private Button spinButton;
	private Button moveRight;
	private Button buttonLeft;

	/**
	 * Constructor - created by SubsystemManager.java
	 * @param drives - Drives Subsystem
	 */
    public TeleopControls(Acquisitions acq, Drives drives, Shooter shooter, Storage storage){
        super(acq, drives, shooter, storage); //Superclass stores for you!!
        
        Joystick joystick = new Joystick(0);
        
        spinButton = new Button(joystick, 1);//Creates button for keeping track of button 0
        moveRight = new Button(joystick, 4);
        spinButton = new Button(joystick, 1);//Creates button for keeping track of button 1
        buttonLeft = new Button(joystick, 3);
        
    }

    @Override
    public void execute() {
    	if(spinButton.get()) {
    		drives.startSpin();
    	}
    	if(moveRight.get()) {
    		drives.turnRight(90);
    	}
    	if(buttonLeft.get()) {
			drives.turnLeft(90);
    	}
    } 

}
