package frc.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;
import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;

public class AutoControl extends Controller{

	private SendableChooser<AutoRoutine> autoSelector;
	private AutoRoutine[] possibleAutos = {
			//Add new Auto Routines here
	};
	private AutoTask[] currentAuto;
	private int autoStep = 0;
	
	public AutoControl(Acquisitions acq, Drives drives, Shooter shooter, Storage storage) {
		super(acq, drives, shooter, storage);
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

	@Override
	public void execute() {
		if(currentAuto == null) { //First time auto has run
			autoStep = 0;
			currentAuto = getSelectedAuto();
		}
		AutoFeature currentTask = currentAuto[autoStep].getFeature();
		switch(currentTask) {
			//Commands go here
		}
	}

}

