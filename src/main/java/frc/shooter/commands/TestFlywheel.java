package frc.shooter.commands;

import frc.drives.DrivesSensorInterface;
import frc.health.HealthReport;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class TestFlywheel extends ShooterCommand{

	private double currentSpeed;
	private double previousSpeed;
	
	public TestFlywheel(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors) {
		super(sensors, driveSensors);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ShooterOutput execute(){
		return new ShooterOutput(20);
		
	}
	@Override 
	public HealthReport checkHealth() {
		
		previousSpeed = currentSpeed;
		currentSpeed = sensors.getShooterSpeed();
		
		boolean isChanging = false;
		boolean correctDirection = false;
		
		if(currentSpeed != previousSpeed) {
			isChanging = true; 
		}
		
		if(sensors.getShooterSpeed() >= 0) {
			correctDirection = true;
		}
		
		if(isChanging = false) {
			return new HealthReport(true, "Speed is not changing!");
		}

		if(correctDirection = false) {
			return new HealthReport(true, "The direection is wrong!");
		}
		
		return new HealthReport();
		
	}
}

