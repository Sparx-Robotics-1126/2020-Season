package frc.shooter.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drives.DrivesSensorInterface;
import frc.health.HealthReport;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class ScanForTarget extends ShooterCommand {

	private boolean movingRight;
	private final int maxAngleOnEitherSide = 110;
	private final int howCloseToEdge = 3;
    private double previousAngle;

    public ScanForTarget(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors){
        super(sensors , driveSensors);
        movingRight = false;
    }

    @Override 
    public ShooterOutput execute() {
        double angle = sensors.getShooterAngleToRobot();
        
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
    	double currentAngle = sensors.getShooterAngleToRobot();
    	HealthReport report = new HealthReport();
    	if(currentAngle == 0) {//Haven't MOVED
    		report = new HealthReport(true, "Turret is not spinning");
    	}
    	if((movingRight && previousAngle > currentAngle) || (!movingRight && previousAngle < currentAngle)) {
    		report = new HealthReport(true, "Turret is not turning in correct direction");
        }
        previousAngle = currentAngle;
    	return report;
    }
}

