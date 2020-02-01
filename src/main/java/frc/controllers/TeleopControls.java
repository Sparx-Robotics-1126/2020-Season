package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import frc.sensors.Limelight;
import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class TeleopControls extends Controller {

	private Button spinButton;
	private Button buttonLeft;
	Limelight l = new Limelight();
	/**
	 * Constructor - created by SubsystemManager.java
	 * @param drives - Drives Subsystem
	 */
    public TeleopControls(Acquisitions acq, Drives drives, Shooter shooter, Storage storage){
        super(acq, drives, shooter, storage); //Superclass stores for you!!
        
        Joystick joystick = new Joystick(0);
        spinButton = new Button(joystick, 1);//Creates button for keeping track of button 0
        buttonLeft = new Button(joystick, 3);
    }

    @Override
    public void execute() {
    	if(spinButton.get()) {
    		drives.startSpin();
    	}
    	if(buttonLeft.get()) {
			drives.turnLeft(90);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			//TODO: handle exception
		}
		System.out.println(l.getDistanceFromTarget());
    } 

}
