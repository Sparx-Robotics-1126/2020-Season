package frc.shooter.commands;

import frc.drives.DrivesSensorInterface;
import frc.health.HealthReport;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class ScanForTarget extends ShooterCommand {

	private boolean movingRight = true;
    final private int maxAngleOnEitherSide = 110;
    final private int howCloseToEdge = 3;
    private double previousAngleToRobot;
    private double currentAngleToRobot;

    public ScanForTarget(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors){
        super(sensors , driveSensors);
        this.currentAngleToRobot = sensors.getShooterAngleToRobot();
        this.previousAngleToRobot = sensors.getShooterAngleToRobot();
    }

    @Override 
    public ShooterOutput execute() {
        double angle = sensors.getShooterAngleToRobot();
        this.previousAngleToRobot = this.currentAngleToRobot;
        this.currentAngleToRobot = angle;
        
        if(angle > maxAngleOnEitherSide - howCloseToEdge) {
        	movingRight = false;
        }
        if(angle < (-maxAngleOnEitherSide) + howCloseToEdge) {
        	movingRight = true;
        }
        return (movingRight)? new ShooterOutput(.2): new ShooterOutput(-.2);
    }
    

    
    @Override
    public HealthReport checkHealth() {
    	
        boolean turningCorrectly = false;
        boolean isTurning = false;
        
    	if (this.currentAngleToRobot != this.previousAngleToRobot) { //if the previous and current angles are different, the turret must be spinning.
    		isTurning = true;
    	}
    	
    	if (!isTurning) {
    		return new HealthReport(true, "Turret is not spinning");
    	}
    	
    	//Else because will fail second check automatically if it fails the first
    	else if ((this.previousAngleToRobot < this.currentAngleToRobot && movingRight) || //if the turret should be spinning right and is spinning right, return true
    	   (this.previousAngleToRobot > this.currentAngleToRobot && !movingRight)) { //if the turret should be spinning left and is spinning left, return true
    		turningCorrectly = true;
    	}
    	
    	if (!turningCorrectly) { 
    		return new HealthReport(true, "Turret is not turning in correct direction");
    	}
    	
    	return new HealthReport();
    }
}

