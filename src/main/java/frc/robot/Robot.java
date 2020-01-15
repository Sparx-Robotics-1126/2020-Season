package frc.robot;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.RobotBase;
import frc.controllers.Controller;
import frc.controllers.TeleopControls;
import frc.subsystem.Drives;

/**
 * Controls when subsystems are engadged as well as gives control to correct controller (auto/teleop/test)
 */
public class Robot extends RobotBase{

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

    private void robotInit(){
        state = RobotState.STANDBY; //When robot turns on, we don't want anything running in the background
        drives = new Drives(); // Creates drives instance
        teleopControls = new TeleopControls(drives); //Creates controller instance, passes in drives subsystem
        new Thread(drives).start(); //Starts drive process on its own thread, Calls "run" method continuously
    }

    private void disabledStarted(){
        state = RobotState.STANDBY;
    }

    /**
     * Called by Robot.java when auto has been started
     */
    private void autoStarted(){
        state = RobotState.AUTO;
        //currentController = autoController;
    }

    /**
     * Called by Robot.java when teleop has been started
     */
    private void teleopStarted(){
        state = RobotState.TELE;
        currentController = teleopControls;
    }

    //Main Method
    private void mainLoop() {
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

    @Override
    public void startCompetition() {
        System.out.println("********ROBOT INIT********");
        HAL.observeUserProgramStarting();
        robotInit();
        System.out.println("************STARING Main Loop************");
        while(true){
            if(!isDisabled()){
                if(isAutonomous() && state != RobotState.AUTO){
                    System.out.println("**********AUTO STARTED************");
                    autoStarted();
                    HAL.observeUserProgramAutonomous();
                }else if(isOperatorControl() && state != RobotState.TELE){
                    teleopStarted();
                    System.out.println("**********TELEOP STARTED************");
                    HAL.observeUserProgramTeleop();
                }
            }else if(state != RobotState.STANDBY){
                System.out.println("**********DISABLED STARTED************");
                disabledStarted();
                HAL.observeUserProgramDisabled();
            }
            mainLoop();
        }

    }

    @Override
    public void endCompetition() {
    
    }
}
