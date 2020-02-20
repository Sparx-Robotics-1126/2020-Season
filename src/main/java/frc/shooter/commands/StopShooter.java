package frc.shooter.commands;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class StopShooter extends ShooterCommand{

	public StopShooter(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
	}

	@Override
	public ShooterOutput execute() {
		return new ShooterOutput(0, false, true);
	}

}
