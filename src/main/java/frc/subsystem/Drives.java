package frc.subsystem;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;
import frc.drives.commands.SpinRight;
import frc.robot.IO;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Used to control ALL drives behavior
 */
public class Drives extends Subsystem{
	
	/**
	 * This is the used to scale the motor output with battery performance
	 */
	private static final double DRIVES_MAX_VOLTAGE = 12.0;

	/**
	 * Current drives action being run
	 * Example: DrivesForward, Turn Right, HumanControl, etc.
	 */
    private DrivesCommand drivesCommand;
    
    /*
     * Class containing all sensor data for drives
     * Includes: Encoders, Limit Switches, HumanInput, etc.
     */
    private DrivesSensorInterface drivesSensors;

    //PUT MOTOR INIT HERE
    private TalonSRX rightMotorMaster;  
    private TalonSRX leftMotorMaster;
    
    
    //Main Constructor called in SubsystemManager.java
    public Drives(){
        drivesSensors = new DrivesSensors();
        rightMotorMaster = new TalonSRX(IO.RIGHT_MOTOR_1);
        TalonSRX rightMotorSlave = new TalonSRX(IO.RIGHT_MOTOR_2);
        configureMotor(rightMotorMaster, rightMotorSlave);
        
        leftMotorMaster = new TalonSRX(IO.LEFT_MOTOR_1);
        TalonSRX leftMotorSlave = new TalonSRX(IO.LEFT_MOTOR_2);
        configureMotor(leftMotorMaster, leftMotorSlave);
    }
    
    /**
     * Configures motors to follow one controller
     */
    private static void configureMotor(TalonSRX master, TalonSRX... slaves) {
    	int masterId = master.getDeviceID();
    	master.set(ControlMode.PercentOutput, 0);
    	master.configVoltageCompSaturation(DRIVES_MAX_VOLTAGE);
		master.enableVoltageCompensation(true);
    	for(TalonSRX slave: slaves) {
    		slave.set(ControlMode.Follower, masterId);
    	}
    }

    /**
     * Main loop of drives, is called constantly by the Thread
     * DON'T CALL THIS!!!!
     */
    @Override
    void execute() {
        if(drivesCommand != null) {
            DrivesOutput output = drivesCommand.execute();
            leftMotorMaster.set(ControlMode.PercentOutput, output.getLeftMotor());
            rightMotorMaster.set(ControlMode.PercentOutput, -output.getRightMotor());
            if(output.isDone()) {
            	leftMotorMaster.set(ControlMode.PercentOutput, 0);
            	rightMotorMaster.set(ControlMode.PercentOutput, 0);
            	drivesCommand = null;
            }
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
    
    public void moveForward(double distance) {
    	
    }
    
    public void moveBackward(double distance) {
    	
    }
    
    public void turnRight(double angle) {
    	System.out.println("hi");
    }
    
    public void turnLeft(double angle) {
    	
    }
    
    /**
     * Called by controller to start 180 degree right spin
     */
    public void startSpin() {
    	drivesCommand = new SpinRight(drivesSensors, 0.5, 180);
    }
    
}