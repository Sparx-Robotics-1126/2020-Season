package frc.drives;

import frc.drives.commands.SpinRight;
import frc.robot.Subsystem;

/**
 * Used to control ALL drives behavior
 */
public class Drives extends Subsystem{

	/**
	 * Current drives action beig run
	 * Example: DrivesForward, Turn Right, HumanControl, etc.
	 */
    private DrivesCommand drivesCommand;
    
    /*
     * Class containing all sensor data for drives
     * Includes: Encoders, Limit Switches, HumanInput, etc.
     */
    private DrivesSensorInterface drivesSensors;

    //Main Constructor called in SubsystemManager.java
    public Drives(){
//        drivesSensors = new DrivesSensors();
    }

    /**
     * Main loop of drives, is called constantly by the Thread
     * DON'T CALL THIS!!!!
     */
    @Override
    public void execute() {
        if(drivesCommand != null) {
            DrivesOutput output = drivesCommand.execute();
            //Set left motor
            //set right motor
            //set isDone flag
            //If isDone set state to null
        }
    }

    /**
     * Signals when the current command is done executing (If applicable)
     */
    @Override
    public boolean isDone() {
        //How can we tell if the subsystem is ready to accept a new command?
    	return false;
    }
    
    public void setJoysticks(double left, double right) {
    	//TODO: Finish
    }
    
    /**
     * Called by controller to start 180degree right spin
     */
    public void startSpin() {
    	drivesCommand = new SpinRight(drivesSensors, 0.5, 180);
    }
    
}