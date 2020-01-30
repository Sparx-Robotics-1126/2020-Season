package frc.shooter.commands;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class LimelightTurret extends ShooterCommand {    
	double deadband = 2;
    double tx;
    final double p = .02;

    public LimelightTurret(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors){
        super(sensors, driveSensors);
    }

    @Override
    public ShooterOutput execute(){
        tx = sensors.getAngleToTarget();
        double speed = 0; 
        if(Math.abs(tx) > deadband) {
              speed = tx*p; 
        }
        return new ShooterOutput(speed);
    }

}
    