package frc.subsystem;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class Shooter extends Subsystem{


	private ShooterCommand shooterCommand;
	private ShooterCommand turretCommand;
	private DrivesSensorInterface driveSensors;
	private ShooterSensorsInterfeace shooterSensors;
	private boolean readyToShoot;
	
	public Shooter(DrivesSensorInterface driveSensors, ShooterSensorsInterfeace shooterSensors) {
		this.driveSensors = driveSensors;
		this.shooterSensors = shooterSensors;
		shooterCommand = null;
	}
	
	@Override
	void execute() {
		if(shooterCommand != null) {
			ShooterOutput shooterOutput = shooterCommand.execute();
			ShooterOutput turretOutput = turretCommand.execute();
			readyToShoot = shooterOutput.isReadyToShoot() && turretOutput.isReadyToShoot();
 			//Set Motor Values
		}
	}

	@Override
	public boolean isDone() {
		return readyToShoot || shooterCommand == null;
	}

}
