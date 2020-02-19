package frc.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;
import frc.auto.routine.DoNothing;
import frc.subsystem.Acquisitions;
import frc.subsystem.Climbing;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class AutoControl extends Controller{

	private SendableChooser<AutoRoutine> autoSelector;
	private AutoRoutine[] possibleAutos = {
			new DoNothing()
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
		for(AutoRoutine autos: possibleAutos) {
			autoSelector.addOption(autos.getAutoName(), autos);
		}
		SmartDashboard.putData(autoSelector);
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
		case SHOOTER_DONE:
			if(!shooter.isDone()) {
				autoStep--;
			}
			break;
		default:
			System.out.println("Unimplemented Feature: " + currentTask);
		}
		autoStep++;
	}

}

