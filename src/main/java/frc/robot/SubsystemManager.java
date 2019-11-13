package frc.robot;

import frc.controllers.Controller;
import frc.controllers.TeleopControls;
import frc.drives.Drives;

/**
 * Controls when subsystems are engadged as well as gives control to correct controller (auto/teleop/test)
 */
public class SubsystemManager implements Runnable {

    //States robot can be in
    public enum RobotState{
		STANDBY,
		AUTO,
		TELE;
	}

    //Controllers
    private TeleopControls teleopControls;
    
    //Robot Subsystems
    private Drives drives;

    //Acting Controller (Auto/Teleop/Test)
    private Controller currentController;

    //Keeps track of current state
    private RobotState state;

    /**
     * Constructor (Created in Robot.java)
     */
    public SubsystemManager(){
        state = RobotState.STANDBY; //When robot turns on, we don't want anything running in the background
        drives = new Drives(); // Creates drives instance
        teleopControls = new TeleopControls(drives); //Creates controller instance, passes in drives subsystem
        new Thread(drives).start(); //Starts drive process on its own thread, Calls "run" method continuously
        new Thread(this).start();//Calls "run" method continuously
    }

    /**
     * Called by Robot.java when auto has been started
     */
    public void autoStarted(){
        state = RobotState.AUTO;
        //currentController = autoController;
    }

    /**
     * Called by Robot.java when teleop has been started
     */
    public void teleopStarted(){
        state = RobotState.TELE;
        currentController = teleopControls;
    }

    //This is called in the constructor Thread.start();
    //Main Method
    @Override
    public void run() {
        switch (state){
            case STANDBY:
                return;
            case AUTO:
                //This can be used to give grive control after done
            	//Also used if semi-auto things are happening
            	//NOTICE THERE IS NO BREAK HERE
            case TELE:
                currentController.execute(); //Calls the current controller (auto/teleop/test)
        }
    }
}
