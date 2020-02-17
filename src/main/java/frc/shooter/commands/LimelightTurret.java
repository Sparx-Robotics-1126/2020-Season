package frc.shooter.commands;

import com.fasterxml.jackson.core.StreamReadFeature;

import frc.drives.DrivesSensorInterface;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class LimelightTurret extends ShooterCommand {    
	private final double DEADBAND = .25;
	private final double MAX_ANGLE = 100;   
    final double p = .05;

    public LimelightTurret(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors){
        super(sensors, driveSensors);
    }

    @Override
    public ShooterOutput execute(){
        double tx = sensors.getAngleToTarget();
        double speed = 0; 
        if(Math.abs(tx) > DEADBAND) {
              speed = tx*p; 
        }
        
        
        double shooterAngle = sensors.getShooterAngleToRobot();


        if(speed>0) {
        	if(shooterAngle > MAX_ANGLE) {
        		speed = 0;
        	}
        }else{
        	if(shooterAngle < -MAX_ANGLE) {
        		speed = 0;
        	}
        }
        return new ShooterOutput(speed);
    }

}
    