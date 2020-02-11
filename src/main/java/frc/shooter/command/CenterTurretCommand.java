package frc.shooter.command;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class CenterTurretCommand extends ShooterCommand {
	
	private final double pK = 0.05;
	
	public CenterTurretCommand(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
		
	}

	@Override
	public ShooterOutput execute() {
		if (Math.abs(sensors.getShooterAngleToRobot()) > 1) {
			return new ShooterOutput(-sensors.getShooterAngleToRobot() * pK, false);
		}
		return new ShooterOutput(0, false);
	}
}
