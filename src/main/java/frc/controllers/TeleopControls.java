package frc.controllers;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.controllers.Button.ButtonType;
import frc.sensors.Limelight;
import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class TeleopControls extends Controller {

	private Joystick driverJoystick;
	private Axis driverLeftAxis;
    private Axis driverRightAxis;
    private Button driverClimbingExtend;
    private Button driverClimbingRetract;
    private Button driverClimbingWinch;
    
    private Joystick operatorJoystick;
    private AxisButton operatorPrepareToFireStart;
    private AxisButton operatorPrepareToFirePressed;
    private AxisButton operatorPrepareToFireEnd;
    private AxisButton operatorFire;
    private Button operatorAcqAcquire;
    private Button operatorAcqStopAcquire;
	
	/**
	 * Constructor - created by SubsystemManager.java
	 * @param drives - Drives Subsystem
	 */
    public TeleopControls(Acquisitions acq, Climbing climbing, Drives drives, Shooter shooter, Storage storage){
        super(acq, climbing, drives, shooter, storage); //Superclass stores for you!!
        
        driverJoystick = new Joystick(0);
        operatorJoystick = new Joystick(1);

        //DRIVER
        driverLeftAxis = new Axis(driverJoystick, ControllerMappings.XBOX_LEFT_Y, true);
        driverRightAxis = new Axis(driverJoystick, ControllerMappings.XBOX_RIGHT_Y, true);
        driverClimbingExtend = new Button(driverJoystick, ControllerMappings.XBOX_Y);
        driverClimbingRetract = new Button(driverJoystick, ControllerMappings.XBOX_A);
        driverClimbingWinch = new Button(driverJoystick, ControllerMappings.XBOX_B);
        
        
        //OPERATOR
        operatorPrepareToFireStart = new AxisButton(operatorJoystick, ControllerMappings.XBOX_L2, ButtonType.RISING_EDGE);
        operatorPrepareToFirePressed = new AxisButton(operatorJoystick, ControllerMappings.XBOX_L2, ButtonType.PRESSED);
        operatorPrepareToFireEnd = new AxisButton(operatorJoystick, ControllerMappings.XBOX_L2, ButtonType.FALLING_EDGE);
        operatorFire = new AxisButton(operatorJoystick, ControllerMappings.XBOX_R2, ButtonType.PRESSED);
        operatorAcqAcquire = new Button(operatorJoystick, ControllerMappings.XBOX_A);
        operatorAcqStopAcquire = new Button(operatorJoystick, ControllerMappings.XBOX_B);
    }

    @Override
    public void execute() {
    	//Driver
    	drives.setJoysticks(driverLeftAxis.get(), driverRightAxis.get());
    	if(driverClimbingExtend.get()) {
    		climbing.extendScissorLift();
    	}
    	if(driverClimbingRetract.get()) {
    		climbing.retractScissorLift();
    	}
    	if(driverClimbingWinch.get()) {
    		climbing.startWinch();
    	}
    	
    	//Operator
    	if(operatorPrepareToFireStart.get()) {
    		shooter.startLimelightAiming();
    		storage.prepareForShooting();
    	}
    	if(operatorFire.get() && shooter.isReadyToShoot() && storage.isDone()) {
    		storage.shoot();
    	}
    	if(operatorPrepareToFireEnd.get()) {
    		storage.indexBalls();
    		shooter.centerTurret();
    		setRumble(driverJoystick, 0);
    		setRumble(operatorJoystick, 0);
    	}
    	if(operatorAcqAcquire.get()) {
    		storage.indexBalls();
    		acq.startIntake();
    		setRumble(operatorJoystick, 0.1);
    	}
    	if(operatorAcqStopAcquire.get()) {
    		acq.stopRollers();
    		setRumble(operatorJoystick, 0);
    	}
    	
    	//RUMBLE
    	if(operatorPrepareToFirePressed.get()) {
    		double rumbleValue = 0;
    		if(operatorFire.get()) {
    			rumbleValue = 0.75;
    		}else if(shooter.isReadyToShoot()) {
    			rumbleValue = 0.25;
    		}
    		setRumble(driverJoystick, rumbleValue);
    		setRumble(operatorJoystick, rumbleValue);
    	}
    }
    
    private void setRumble(Joystick joy, double value) {
    	joy.setRumble(RumbleType.kLeftRumble, value);
		joy.setRumble(RumbleType.kRightRumble, value);
    }

}