package frc.shooter.commands;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class ScannerTarget extends ShooterCommand {
	private ShooterCommand scan;
	private ShooterCommand target;
	public ScannerTarget(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors, ShooterCommand scan, ShooterCommand target) {
		super(sensors, driveSensors);
		this.scan = scan;
		this.target = target;
	}

	@Override
	public ShooterOutput execute() {
		if (sensors.getTargetLock() == true) {
			return target.execute();
		}
		
		return scan.execute();
	}
}
