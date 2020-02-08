package frc.shooter;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterOutput;

public class CenterTurretCommand extends ShooterCommand {
	
	public CenterTurretCommand(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
		
	}

	@Override
	public ShooterOutput execute() {
		if (sensors.getShooterAngleToRobot() > 0) {
			return new ShooterOutput(0.0, 0.0, false);
		} else if (sensors.getShooterAngleToRobot() < 0) {
			return new ShooterOutput(0.0, 0.0, false);
			
		} else return null;
	}
}
