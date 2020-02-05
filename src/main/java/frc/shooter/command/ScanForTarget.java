import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;
import frc.sensors.Limelight;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorsInterfeace;

public class ScanForTarget extends ShooterCommand {

	private boolean movingRight = true;
    final private int maxAngleOnEitherSide = 160;
    final private int howCloseToEdge = 3;

    public ScanForTarget(ShooterSensorsInterfeace sensors, DrivesSensorInterface driveSensors){
        super(sensors , driveSensors);
        l = new Limelight();
    }

    @Override 
    public ShooterOutput execute() {
        double angle = sensors.getShooterAngleToRobot();
        
        if(angle>maxAngleOnEitherSide-howCloseToEdge) {
        	movingRight = false;
        }
        if(angle<-maxAngleOnEitherSide+howCloseToEdge) {
        	movingRight = true;
        }
        return (movingRight)? ShooterOutput(.2): ShooterOutput(-.2);
    }
}