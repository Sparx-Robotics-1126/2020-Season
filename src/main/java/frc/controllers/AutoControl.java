package frc.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;
import frc.auto.routine.DoNothing;
<<<<<<< HEAD
import frc.auto.routine.DriveBackwards;
=======
import frc.auto.routine.ShootBallsOnly;
>>>>>>> 6e951cf2d68b307b874eaccb7966e8a086afbcda
import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class AutoControl extends Controller{

	private SendableChooser<AutoRoutine> autoSelector;
	private AutoRoutine[] possibleAutos = {
			new DoNothing(),
			new DriveBackwards(),
			new ShootBallsOnly()
			//Add new Auto Routines here
	};
	private AutoTask[] currentAuto;
	private int autoStep = 0;

	public AutoControl(Acquisitions acq, Climbing climbing, Drives drives, Shooter shooter, Storage storage) {
		super(acq, climbing, drives, shooter, storage);
		createDashboard();
	}

	private void createDashboard() {
		autoSelector = new SendableChooser<AutoRoutine>();
		for(int i = 0; i < possibleAutos.length; i++) {
			AutoRoutine auto = possibleAutos[i];
			autoSelector.addOption(auto.getAutoName(), auto);
		}
		SmartDashboard.putData("Auto Selector", autoSelector);
	}

	private AutoTask[] getSelectedAuto() {
		AutoRoutine auto = autoSelector.getSelected();
		System.out.println("Auto Selected: " + auto.getAutoName());
		return auto.getAutoSequence();
	}
	
	public void resetAuto() {
		currentAuto = null;
		autoStep = 0;
	}

	@Override
	public void execute() {
		if(currentAuto == null) { //First time auto has run
			autoStep = 0;
			currentAuto = getSelectedAuto();
		}
		AutoTask currentTask = currentAuto[autoStep];
		AutoFeature currentFeature = currentTask.getFeature();
		switch(currentFeature) {
		//Commands go here
		case STOP:
			autoStep--;//This offsets the autostep increment at the bottom causing the step to remain stuck here
			break;
		
		//ACQ
		case ACQ_ACQUIRE:
			acq.startIntake();
			break;
		case ACQ_STOP_ACQUIRING:
			acq.stopRollers();
			break;
		case ACQ_DONE:
			if(!acq.isDone()) {
				autoStep--;
			}
			break;

		//DRIVES
		case DRIVE_FORWARD:
			// drives.
			break;
		case DRIVE_BACKWARDS:
			drives.moveBackward(currentTask.value1);
			break;
		case DRIVE_TURN_LEFT:
			drives.turnLeft(currentTask.value1);
			break;
		case DRIVE_TURN_RIGHT:
			drives.turnRight(currentTask.value1);
			break;
		case DRIVE_DONE:
			if(!drives.isDone()){
				autoStep--;
			}
			break;
			
		
		//SHOOTER
		case SHOOTER_ACTIVATE_LIMELIGHT:
			shooter.startLimelightAiming();
			break;
		case SHOOTER_DEACTIVATE_LIMELIGHT:
			shooter.centerTurret();
			break;
		case SHOOTER_READY_TO_FIRE:
			if(!shooter.isReadyToShoot()) {
				autoStep--;
			}
			break;
		case SHOOTER_DONE:
			if(!shooter.isDone()) {
				autoStep--;
			}
			break;

		//STORAGE
		case STORAGE_PREPARE_FOR_SHOOTING:
			storage.prepareForShooting();
			break;
		case STORAGE_SHOOT:
			storage.shoot();
			break;
		case STORAGE_DONE:
			if(!storage.isDone()) {
				autoStep--;
			}
			break;
			
		default:
			System.out.println("Unimplemented Feature: " + currentTask);
		}
		autoStep++;
	}
}

