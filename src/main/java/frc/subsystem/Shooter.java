package frc.subsystem;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	
	public Shooter(DrivesSensorInterface driveSensors, ShooterSensorsInterfeace shooterSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = shooterSensors;
		shooterCommand = null;
	}
	
	@Override
	void execute() {
		if(shooterCommand != null) {
			ShooterOutput output = shooterCommand.execute();
			//Set Motor Values
		}
	}

	@Override
	public boolean isDone() {
		return shooterCommand == null;
	}

}
