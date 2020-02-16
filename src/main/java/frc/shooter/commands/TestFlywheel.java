package frc.shooter.commands;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class TestFlywheel extends ShooterCommand{

	public TestFlywheel(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ShooterOutput execute(){
		return new ShooterOutput(20);
		
	}
}

