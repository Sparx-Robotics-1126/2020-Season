package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import frc.sensors.Limelight;
import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class TeleopControls extends Controller {

	private Axis leftAxis;
    private Axis rightAxis;
	
	/**
	 * Constructor - created by SubsystemManager.java
	 * @param drives - Drives Subsystem
	 */
    public TeleopControls(Acquisitions acq, Climbing climbing, Drives drives, Shooter shooter, Storage storage){
        super(acq, climbing, drives, shooter, storage); //Superclass stores for you!!
        
        Joystick driverJoystick = new Joystick(0);
        Joystick operatorJoystick = new Joystick(1);

        leftAxis = new Axis(driverJoystick, 1, true);
        rightAxis = new Axis(driverJoystick, 5, true);
        
    }

    @Override
    public void execute() {
    	drives.setJoysticks(leftAxis.get(), rightAxis.get());
    } 

}