package frc.robot;

import frc.controllers.Controller;
import frc.controllers.TeleopControls;
import frc.subsystems.Drives;

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

    //Robot Subsystems
    private TeleopControls teleopControls;
    private Drives drives;

    //Acting Controller (Auto/Teleop/)
    private Controller currentController;

    //Keeps track of current state
    private RobotState state;

    public SubsystemManager(){
        state = RobotState.STANDBY;
        drives = new Drives();
        teleopControls = new TeleopControls(drives);
        new Thread(drives).start();
        new Thread(this).start();//Calls "run" method continuously
    }

    public void autoStarted(){
        state = RobotState.AUTO;
        //currentController = autoController;
    }

    public void teleopStarted(){
        state = RobotState.TELE;
        currentController = teleopControls;
    }

    //This is called in the constructor Thread.start();
    @Override
    public void run() {
        switch (state){
            case STANDBY:
                return;
            case AUTO:
                //THIS CAN BE USED TO GIVE DRIVER CONTROL AFTER DONE
            case TELE:
                currentController.execute();
        }
    }
}
