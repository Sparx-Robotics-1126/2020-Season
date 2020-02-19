package frc.robot;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.RobotBase;
import frc.controllers.AutoControl;
import frc.controllers.Controller;
import frc.controllers.TeleopControls;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;
import frc.shooter.ShooterSensors;
import frc.shooter.ShooterSensorsInterfeace;
import frc.storage.StorageSensorInterface;
import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

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
    private AutoControl autoControls;
    
    //Robot Subsystems
    private Acquisitions acq;
    private Climbing climbing;
    private Drives drives;
    private Shooter shooter;
    private Storage storage;

    //Acting Controller (Auto/Teleop/Test)
    private Controller currentController;

    //Keeps track of current state
    private RobotState state;

    private void robotInit(){
        state = RobotState.STANDBY; //When robot turns on, we don't want anything running in the background
        
        //Sensors
        DrivesSensorInterface drivesSensors = new DrivesSensors();
        
        //Subsystems
        acq = new Acquisitions();
        climbing = new Climbing();
        drives = new Drives(drivesSensors); // Creates drives instance
        shooter = new Shooter(drivesSensors);
        storage = new Storage();
        
        //Controls
        teleopControls = new TeleopControls(acq, climbing, drives, shooter, storage); //Creates controller instance, passes in drives subsystem
        autoControls = new AutoControl(acq, climbing, drives, shooter, storage);
        
        //Starting Subsystems
        new Thread(acq).start();
        new Thread(climbing).start();
        new Thread(drives).start();
        new Thread(shooter).start();
        new Thread(storage).start();
    }

    private void disabledStarted(){
        state = RobotState.STANDBY;
        autoControls.resetAuto();
    }

    /**
     * Called by Robot.java when auto has been started
     */
    private void autoStarted(){
        state = RobotState.AUTO;
        currentController = autoControls;
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
                System.out.println("**********ROBOT DISABLED************");
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
